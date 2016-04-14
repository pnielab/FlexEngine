grammar FlexGrammar1;


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
        String regexResult = $regex.text;
        regexResult = regexResult.substring(1, regexResult.length()-1);
        configMetaData.setParseString(regexResult);
        configMetaData.setParsingMethod(ParsingMethod.REGEX);
}

TOKEN_COUNT tokenCount = ANY_TEXT {
        String tokenCountResult = $tokenCount.text;
        tokenCountResult = tokenCountResult.substring(1, tokenCountResult.length()-1);
        configMetaData.setTokenCount(tokenCountResult);
}

 tokens= predicate {configMetaData.setTokens($tokens.tokenObj);}
/*    | tokenCountExpression  {$expression = $tokenCountExpression.expression; parseResponse.setTokenCount($expression);}
 | tokenExpression         {$expression = $tokenExpression.expression; parseResponse.setTokens($expression);}*/
;

/**
 * Tokens
 */



WS              : [ \r\t\n]+ -> channel(WHITESPACE);
REGEX           : 'regex';
TOKEN_COUNT     : 'token.count';
// need to talk about the number as a token
NUMBER          : ('0'..'9')+ '.' ('0'..'9')+  | ('0'..'9')+;
TOKEN_NAME      : 'token[' index1=NUMBER '].name';
TOKEN_TYPE      : 'token[' index2=NUMBER '].type';
TOKEN_FORMAT    : 'token[' index3=NUMBER '].format';
ANY_TEXT        : '=' ~([ \r\t\n])+ '\n';



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

   TOKEN_NAME name=ANY_TEXT {
        String nameResult = $name.text;
        nameResult = nameResult.substring(1, nameResult.length()-1);
   }
   (TOKEN_TYPE type = ANY_TEXT)? {
        String typeResult = $type.text;
        if(typeResult != null){
            typeResult = typeResult.substring(1, typeResult.length()-1);
        }
   }

  (TOKEN_FORMAT format = ANY_TEXT)? {
       String formatResult = $format.text;
       if(formatResult != null){
           formatResult = formatResult.substring(1, formatResult.length()-1);
       }
  }
  {$tokenObj = MetaDataFactory.createTokenMetaData(nameResult, typeResult, formatResult, $index1.text, $index2.text, $index3.text);}
;

tokensList returns [List<TokenMetaData> flexTokens]
  @init {
      $flexTokens = new ArrayList<>();
  }:
  a=token {$flexTokens.add($a.tokenObj); } ( b=token {$flexTokens.add($b.tokenObj);})*
;
