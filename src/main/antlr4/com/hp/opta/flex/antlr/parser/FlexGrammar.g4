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
 regex = regexExpression  {configMetaData.setParseString($regex.expression);configMetaData.setParsingMethod(ParsingMethod.REGEX);}
 tokenCount = tokenCountExpression {configMetaData.setTokenCount($tokenCount.expression);}
 tokens= predicate {configMetaData.setTokens($tokens.tokenObj);}
/*    | tokenCountExpression  {$expression = $tokenCountExpression.expression; parseResponse.setTokenCount($expression);}
 | tokenExpression         {$expression = $tokenExpression.expression; parseResponse.setTokens($expression);}*/
;


regexExpression returns [String expression] :
 'regex=' regex=.*
 {$expression = $regex.text;}
;

tokenCountExpression returns [Integer expression] :
 'token.count=' count=NUMBER
 {$expression = Integer.parseInt($count.text);}
;

/**
 * Tokens
 */


NOT             : 'NOT';
ISNULL          : 'ISNULL';
AND             : 'AND';
OR              : 'OR';
IN              : 'IN';
IS              : 'IS';
NULL            : 'NULL';
PIPE            : '|';
LEFT_PARENTHESIS: '(';
RIGHT_PARENTHESIS: ')';
ASTERISK        : '*';
LEFT_BRACKET    : '[';
RIGHT_BRACKET   : ']';
HYPHEN          : '-';


WS              : [ \r\t\n]+ -> channel(WHITESPACE);
NUMBER          : ('0'..'9')+ '.' ('0'..'9')+  | ('0'..'9')+;
TOKEN_TYPE      : 'String' | 'Long' |'Integer'| 'TimeStamp'| 'Boolean'| 'Double';
STRING          : '\"' (ESCAPE_SEQUENCE | ~('"'))* '\"';
ID              : [a-zA-Z$] [a-zA-Z0-9_]*;

//The name can be in the following format: message_time (all low case, _ between words, no spaces), name_11:00:00
NAME_FORMAT     : ([a-z]*)'_'([0-2][0-4]':'[0-5][0-9]':'[0-5][0-9]);
LITERAL         : ~(' ' | '"' | ',' | '(' | ')' | '{' | '}' | '$' | '[' | ']' | '=' | '>' | '<' | '\n' | '\t' | '\r' | ':' | ';' | '|' | '-' | '+' | '/' | '%' | '^'| '*')+;
ELLIPSES        : '...';
ESCAPE_SEQUENCE : '\\' '\"' { setText("\""); };
RELOPERATOR     : ('<' | '<=' | '=' | '>' | '>='); //what about <>?
FALL_THROUGH    :  .  { reportLexerError( getCharPositionInLine(), getText()); }; // Everything else is wrong, need to be catched here


/**
 * Rules for LDB
 */

ldbExpression:
  predicate {finalLdbExpression = $predicate.tokenObj; }
;


predicate returns [List<TokenMetaData> tokenObj] :
    tokensList {$tokenObj=$tokensList.flexTokens;}
//    token {$tokenObj=$token.tokenObj;}
;


token returns [TokenMetaData tokenObj] :
//  token[3].name=MessageTime
//  token[3].type=TimeStamp
//  token[3].format=yyyy-MM-dd HH\:mm\:ss.sss
    'token[' index1=NUMBER '].name=' name=NAME_FORMAT
    ('token[' index2=NUMBER '].type=' type=TOKEN_TYPE)?
    ('token[' index3=NUMBER '].format=' format=ID)? {$tokenObj = MetaDataFactory.createTokenMetaData($name.text, $type.text, $format.text, $index1.text, $index2.text, $index3.text);}
;

tokensList returns [List<TokenMetaData> flexTokens]
  @init {
      $flexTokens = new ArrayList<>();
  }:
  a=token {$flexTokens.add($a.tokenObj); } ( b=token {$flexTokens.add($b.tokenObj);})*
;

//rexCommand returns [LdbExpression expression] :
//  // rex[Y, ".*?\s(\d+).*"]
//  ldb='rex' LEFT_BRACKET sourceProp=property ',' term RIGHT_BRACKET                                             {$expression = new RexCommand($sourceProp.propery, $term.pattern); }
//  | ldb='rex' LEFT_BRACKET LEFT_PARENTHESIS sourceExpression=predicate RIGHT_PARENTHESIS ',' term RIGHT_BRACKET {$expression = new RexCommand($sourceExpression.expression, $term.pattern); }
//;
//
//property returns [SingleValueExpression propery]:
//  term { $propery = new SingleValueExpression($term.pattern);}
//;
//
//concatCommand returns [LdbExpression expression]:
//    lp=concatCommand  ',' rp=concatCommand      {$expression = new ConcatExpression($lp.expression, $rp.expression);}
//    |  noneConcatCommands                       {$expression = $noneConcatCommands.expression;}
//;
//
//noneConcatCommands returns [LdbExpression expression] :
//    LEFT_PARENTHESIS rexCommand RIGHT_PARENTHESIS               {$expression = $rexCommand.expression;}
//    | property                                                  {$expression = $property.propery;}
//    | LEFT_PARENTHESIS arithmeticExpression RIGHT_PARENTHESIS   {$expression = $arithmeticExpression.expression;}
//;
//
//noneArithmeticCommands returns [LdbExpression expression] :
//   LEFT_PARENTHESIS rexCommand RIGHT_PARENTHESIS        {$expression = $rexCommand.expression;}
//    | property                                          {$expression = $property.propery;}
//    | LEFT_PARENTHESIS concatCommand RIGHT_PARENTHESIS  {$expression = $concatCommand.expression;}
//;
//
//
//
//arithmeticExpression returns [LdbExpression expression] :
//  HYPHEN a=arithmeticExpression                                 { $expression = new NegativeArithmetic(new SingleValueExpression(new ParserPattern("0")), "-", $a.expression); }
//  | LEFT_PARENTHESIS a=arithmeticExpression RIGHT_PARENTHESIS   { $expression = new ParenthesisArithmetic(((ArithmeticExpression)$a.expression).getLeft(),((ArithmeticExpression)$a.expression).getOperator().getString(),((ArithmeticExpression)$a.expression).getRight()); }
//  | la=arithmeticExpression '*' ra=arithmeticExpression         { $expression = new ArithmeticExpression($la.expression, "*", $ra.expression); }
//  | la=arithmeticExpression '%' ra=arithmeticExpression         { $expression = new ArithmeticExpression($la.expression, "%", $ra.expression); }
//  | la=arithmeticExpression '^' ra=arithmeticExpression         { $expression = new ArithmeticExpression($la.expression, "^", $ra.expression); }
//  | la=arithmeticExpression '/' ra=arithmeticExpression         { $expression = new ArithmeticExpression($la.expression, "/", $ra.expression); }
//  | la=arithmeticExpression '+' ra=arithmeticExpression         { $expression = new ArithmeticExpression($la.expression, "+", $ra.expression); }
//  | la=arithmeticExpression '-' ra=arithmeticExpression         { $expression = new ArithmeticExpression($la.expression, "-", $ra.expression); }
//  | noneArithmeticCommands                                      { $expression = $noneArithmeticCommands.expression; }
//;

//numberExpression returns [ParserPattern pattern] :
//  NUMBER  { $pattern = new ParserPattern($NUMBER.text, false); }
//  | ID      { $pattern = new ParserPattern($ID.text); }
//;
//
//
//term returns [ParserPattern pattern] :
//  STRING     {$pattern = new ParserPattern(SingleValueExpression.processStringToken($STRING.text), true); }
//  | NUMBER   {$pattern = new ParserPattern($NUMBER.text, false); }
//  | ID       {$pattern = new ParserPattern($ID.text); }
//  | LITERAL  {$pattern = new ParserPattern($LITERAL.text); }
//;