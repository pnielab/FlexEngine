grammar FlexGrammar;


@header {
import com.hp.opta.flex.antlr.parser.impl.ParserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hp.opta.flex.configuration.model.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.lang.Integer;
}

@members {
  private static final Logger LOG = LoggerFactory.getLogger(ParserImpl.class);

  public static final int WHITESPACE = 2;

  private List<TokenMetaData> finalLdbExpression = null;

  private ConfigurationMetaData configMetaData = new ConfigurationMetaData();

  public String lexerErrorText = "";
  public int lexerErrorPosition = -1;
  public boolean hasLexerError = false;

  // TODO: do I need this?
  void reportLexerError(int pos, String text) {
    hasLexerError = true;
    lexerErrorPosition = pos;
    lexerErrorText = text;
  }


  public String getAnyString(String text){
    return (text == null || text.isEmpty())? text:text.substring(1, text.length()-1);
  }

}

/**
 * Start Symbols
 */

/*parseLdb returns [List<FlexToken> ldbResponce]
    @Init {
      $ldbResponce = finalLdbExpression;
    }:
   (ldbExpression) EOF { $ldbResponce = finalLdbExpression; }
;*/

parseConfigFile returns [ConfigurationMetaData parseResponse]
    @Init {
      $parseResponse = configMetaData;
    }:
   (configMetaDataExpression) EOF { $parseResponse = configMetaData; }
;



configMetaDataExpression :
REGEX regex=ANY_TEXT {
        String regexResult = getAnyString($regex.text);
        configMetaData.setParseString(regexResult);
        configMetaData.setParsingMethod(ParsingMethod.REGEX);
}
TOKEN_COUNT tokenCount = ANY_TEXT {
        String tokenCountResult = getAnyString($tokenCount.text);
        configMetaData.setTokenCount(Integer.parseInt(tokenCountResult));
}
 tokens= predicate {configMetaData.setTokens($tokens.tokenObj);}
;






/**
 * Tokens
 */


/*
WS              : [ \r\t\n]+ -> channel(WHITESPACE);
REGEX           : 'regex';
TOKEN_COUNT     : 'token.count';
// need to talk about the number as a token
NUMBER          : ('0'..'9')+ '.' ('0'..'9')+  | ('0'..'9')+;
TOKEN_NAME      : 'token[' index1=NUMBER '].name';
TOKEN_TYPE      : 'token[' index2=NUMBER '].type';
TOKEN_FORMAT    : 'token[' index3=NUMBER '].format';
ANY_TEXT        : '=' ~([ \r\t\n])+ '\n';*/


WS                  : [ \r\t\n]+ -> channel(WHITESPACE);
NUMBER              : ('0'..'9')+;
REGEX               : 'regex';
TOKEN_COUNT         : 'token.count';
TOKEN_START         : 'token[';
TOKEN_NAME          : '].name';
TOKEN_TYPE          : '].type';
TOKEN_FORMAT        : '].format';
//ID                  : [a-zA-Z0-9_]+;
//NAME_FORMAT         : ([a-z]*)'_'([0-2][0-4]':'[0-5][0-9]':'[0-5][0-9]);
//TYPE_FORMAT      : 'String' | 'Long' |'Integer'| 'TimeStamp'| 'Boolean'| 'Double';
//DATE_FORMAT         : [,yMdHmsSTZ:'/null ]+;
ANY_TEXT            : '=' ~([ \r\t\n])+ '\n';



/**
 * Rules for LDB
 */

ldbExpression:
  predicate {finalLdbExpression = $predicate.tokenObj; }
;


predicate returns [List<TokenMetaData> tokenObj] :
    tokensList {$tokenObj=$tokensList.flexTokens;}
;


token returns [TokenMetaData tokenObj] :
    TOKEN_START index1=NUMBER TOKEN_NAME name=ANY_TEXT
    (TOKEN_START index2=NUMBER TOKEN_TYPE type=ANY_TEXT)?
    (TOKEN_START index3=NUMBER TOKEN_FORMAT format=ANY_TEXT)?
  {
  String nameResult = getAnyString($name.text);
  String typeResult = getAnyString($type.text);
  String formatResult = getAnyString($format.text);
  $tokenObj = MetaDataFactory.createTokenMetaData(nameResult, typeResult, formatResult, $index1.text, $index2.text, $index3.text);}
;

tokensList returns [List<TokenMetaData> flexTokens]
  @init {
      $flexTokens = new ArrayList<>();
  }:
  a=token {$flexTokens.add($a.tokenObj); } ( b=token {$flexTokens.add($b.tokenObj);})*
;
