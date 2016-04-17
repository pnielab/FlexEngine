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

WS                  : [ \r\t\n]+ -> channel(WHITESPACE);
NUMBER              : ('0'..'9')+;
REGEX               : 'regex';
TOKEN_COUNT         : 'token.count';
TOKEN_START         : 'token[';
TOKEN_NAME          : '].name=';
TOKEN_TYPE          : '].type=';
TOKEN_FORMAT        : '].format=';
TYPE_FORMAT         : 'String' | 'Long' |'Integer'| 'TimeStamp'| 'Boolean'| 'Double';
ID                  : [a-zA-Z0-9_]+;
DATE_FORMAT         : [,yMdHmsSTZ:'/null ]+;
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

   TOKEN_START index1=NUMBER TOKEN_NAME name=ID
    (TOKEN_START index2=NUMBER TOKEN_TYPE type=TYPE_FORMAT)?
    (TOKEN_START index3=NUMBER TOKEN_FORMAT format=DATE_FORMAT)?

  {
  $tokenObj = MetaDataFactory.createTokenMetaData($name.text, $type.text, $format.text, $index1.text, $index2.text, $index3.text);}
;

tokensList returns [List<TokenMetaData> flexTokens]
  @init {
      $flexTokens = new ArrayList<>();
  }:
  a=token {$flexTokens.add($a.tokenObj); } ( b=token {$flexTokens.add($b.tokenObj);})*
;
