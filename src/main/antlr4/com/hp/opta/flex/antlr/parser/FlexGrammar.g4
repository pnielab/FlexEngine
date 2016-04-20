grammar FlexGrammar;


@header {
import com.hp.opta.flex.antlr.parser.impl.ParserImpl;
import com.hp.opta.flex.antlr.utils.ParserUtils;
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

  private ConfigurationMetaData configMetaData = null;


  private String parsingString;

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

parseConfigFile returns [ConfigurationMetaData parseResponse]
    @Init {
      $parseResponse = configMetaData;
    }:
   (configMetaDataExpression) EOF { $parseResponse = configMetaData; }
;



configMetaDataExpression :
REGEX parseString=ANY_TEXT {
        // saving regex to be later set when recivin token count.
        parsingString = ParserUtils.getAnyString($parseString.text);
        }
TOKEN_COUNT tokenCount = ANY_TEXT {
        String tokenCountResult = ParserUtils.getAnyString($tokenCount.text);
        int tokenCountResultAsInt = Integer.parseInt(tokenCountResult);
        configMetaData = ParserUtils.createConfigurationMetaDataIfNull(configMetaData, tokenCountResultAsInt);
        // adding the regex expretion that was previously set
        configMetaData.setParseString(parsingString);
}
 addAllTokens
;

token returns [TokenMetaData tokenObj] :

   TOKEN_START index1=NUMBER TOKEN_NAME name=ID
    (TOKEN_START index2=NUMBER TOKEN_TYPE type=TYPE_FORMAT)?
    (TOKEN_START index3=NUMBER TOKEN_FORMAT format=ANY_TEXT)?

  {
  String formatResult = ParserUtils.getAnyString($format.text);
  $tokenObj = MetaDataFactory.createTokenMetaData($name.text, $type.text, formatResult, $index1.text, $index2.text, $index3.text, configMetaData);}
;


addAllTokens:
  a=token {
  MetaDataFactory.addTokenMetaData($a.tokenObj, configMetaData);
  } ( b=token {MetaDataFactory.addTokenMetaData($b.tokenObj, configMetaData);})*
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
TOKEN_FORMAT        : '].format';
TYPE_FORMAT         : 'String' | 'Long' |'Integer'| 'TimeStamp'| 'Boolean'| 'Double';
ID                  : [a-zA-Z0-9_]+;
DATE_FORMAT         : [,yMdHmsSTZ:'/null ]+;
ANY_TEXT            : '=' ~([ \r\t\n])+ '\n';




