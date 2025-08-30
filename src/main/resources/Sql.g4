grammar AdvancedSql;






parse: sql_stmt_list EOF;

sql_stmt_list: sql_stmt (';' sql_stmt)* ';'?;

sql_stmt
    : select_stmt
    | insert_stmt
    | update_stmt
    | delete_stmt
    | create_table_stmt
    | create_view_stmt
    | create_index_stmt
    | drop_stmt
    | alter_table_stmt
    | truncate_stmt
    | with_stmt
    | explain_stmt
    | show_stmt
    | use_stmt
    ;


with_stmt: 'WITH' 'RECURSIVE'? with_clause (',' with_clause)* select_stmt;

with_clause: identifier ('(' column_list ')')? 'AS' '(' select_stmt ')';


select_stmt
    : select_core (compound_operator select_core)*
      order_by_clause?
      limit_clause?
      offset_clause?
    ;

select_core
    : 'SELECT' distinct_clause? select_list
      from_clause?
      where_clause?
      group_by_clause?
      having_clause?
      window_clause?
    ;

distinct_clause: 'DISTINCT' | 'ALL';

select_list: select_item (',' select_item)*;

select_item
    : '*'                                           # SelectAll
    | table_name '.' '*'                           # SelectTableAll
    | expr ('AS'? column_alias)?                   # SelectExpr
    ;

from_clause: 'FROM' table_source (',' table_source)*;

table_source
    : table_name ('AS'? table_alias)?              # TableSourceTable
    | '(' select_stmt ')' ('AS'? table_alias)?     # TableSourceSubquery
    | table_source join_type? 'JOIN' table_source 'ON' expr  # TableSourceJoin
    | table_source 'CROSS' 'JOIN' table_source    # TableSourceCrossJoin
    ;

join_type
    : 'INNER'
    | 'LEFT' 'OUTER'?
    | 'RIGHT' 'OUTER'?
    | 'FULL' 'OUTER'?
    ;

where_clause: 'WHERE' expr;

group_by_clause: 'GROUP' 'BY' grouping_element (',' grouping_element)*;

grouping_element
    : expr
    | 'ROLLUP' '(' expr_list ')'
    | 'CUBE' '(' expr_list ')'
    | 'GROUPING' 'SETS' '(' grouping_set (',' grouping_set)* ')'
    ;

grouping_set
    : '(' expr_list? ')'
    | expr
    ;

having_clause: 'HAVING' expr;

window_clause: 'WINDOW' window_definition (',' window_definition)*;

window_definition: identifier 'AS' window_specification;

window_specification
    : '(' partition_by_clause? order_by_clause? frame_clause? ')'
    ;

partition_by_clause: 'PARTITION' 'BY' expr_list;

order_by_clause: 'ORDER' 'BY' order_item (',' order_item)*;

order_item: expr ('ASC' | 'DESC')? ('NULLS' ('FIRST' | 'LAST'))?;

frame_clause
    : ('ROWS' | 'RANGE' | 'GROUPS')
      (frame_start | 'BETWEEN' frame_start 'AND' frame_end)
    ;

frame_start
    : 'UNBOUNDED' 'PRECEDING'
    | INTEGER 'PRECEDING'
    | 'CURRENT' 'ROW'
    ;

frame_end
    : 'UNBOUNDED' 'FOLLOWING'
    | INTEGER 'FOLLOWING'
    | 'CURRENT' 'ROW'
    ;

limit_clause: 'LIMIT' (expr | 'ALL');

offset_clause: 'OFFSET' expr ('ROW' | 'ROWS')?;

compound_operator: 'UNION' 'ALL'? | 'INTERSECT' | 'EXCEPT';


insert_stmt
    : 'INSERT' 'INTO' table_name ('(' column_list ')')?
      (values_clause | select_stmt)
      on_conflict_clause?
    ;

values_clause: 'VALUES' value_tuple (',' value_tuple)*;

value_tuple: '(' expr_list ')';

on_conflict_clause
    : 'ON' 'CONFLICT' ('(' column_list ')')? conflict_action
    ;

conflict_action
    : 'DO' 'NOTHING'
    | 'DO' 'UPDATE' 'SET' assignment_list where_clause?
    ;


update_stmt
    : 'UPDATE' table_name ('AS'? table_alias)?
      'SET' assignment_list
      from_clause?
      where_clause?
    ;

assignment_list: assignment (',' assignment)*;

assignment: column_name '=' expr;


delete_stmt
    : 'DELETE' 'FROM' table_name ('AS'? table_alias)?
      where_clause?
    ;


create_table_stmt
    : 'CREATE' ('TEMP' | 'TEMPORARY')? 'TABLE' if_not_exists?
      table_name '(' table_element_list ')' table_option*
    | 'CREATE' ('TEMP' | 'TEMPORARY')? 'TABLE' if_not_exists?
      table_name 'AS' select_stmt
    ;

if_not_exists: 'IF' 'NOT' 'EXISTS';

table_element_list: table_element (',' table_element)*;

table_element
    : column_definition
    | table_constraint
    ;

column_definition
    : column_name data_type column_constraint*
    ;

data_type
    : 'INTEGER' | 'INT' | 'SMALLINT' | 'BIGINT'
    | 'DECIMAL' ('(' INTEGER (',' INTEGER)? ')')?
    | 'NUMERIC' ('(' INTEGER (',' INTEGER)? ')')?
    | 'REAL' | 'DOUBLE' 'PRECISION'? | 'FLOAT' ('(' INTEGER ')')?
    | 'CHARACTER' ('(' INTEGER ')')? | 'CHAR' ('(' INTEGER ')')?
    | 'CHARACTER' 'VARYING' ('(' INTEGER ')')? | 'VARCHAR' ('(' INTEGER ')')?
    | 'TEXT' | 'CLOB'
    | 'BLOB' | 'BYTEA'
    | 'BOOLEAN' | 'BOOL'
    | 'DATE' | 'TIME' | 'TIMESTAMP' | 'DATETIME'
    | 'INTERVAL'
    | 'JSON' | 'JSONB'
    | 'UUID'
    | 'SERIAL' | 'BIGSERIAL'
    | identifier
    ;

column_constraint
    : 'NOT' 'NULL'
    | 'NULL'
    | 'DEFAULT' default_value
    | 'PRIMARY' 'KEY'
    | 'UNIQUE'
    | 'CHECK' '(' expr ')'
    | references_clause
    | 'AUTO_INCREMENT'
    | 'GENERATED' ('ALWAYS' | 'BY' 'DEFAULT') 'AS' 'IDENTITY' identity_options?
    ;

default_value
    : literal
    | function_call
    | 'CURRENT_TIMESTAMP'
    | 'CURRENT_DATE'
    | 'CURRENT_TIME'
    | 'NULL'
    ;

identity_options: '(' identity_option (',' identity_option)* ')';

identity_option
    : 'START' 'WITH' INTEGER
    | 'INCREMENT' 'BY' INTEGER
    | 'MINVALUE' INTEGER
    | 'MAXVALUE' INTEGER
    | 'CACHE' INTEGER
    | 'CYCLE' | 'NO' 'CYCLE'
    ;

references_clause
    : 'REFERENCES' table_name ('(' column_list ')')?
      ('ON' 'DELETE' reference_action)?
      ('ON' 'UPDATE' reference_action)?
    ;

reference_action
    : 'CASCADE'
    | 'SET' 'NULL'
    | 'SET' 'DEFAULT'
    | 'RESTRICT'
    | 'NO' 'ACTION'
    ;

table_constraint
    : 'PRIMARY' 'KEY' '(' column_list ')'
    | 'FOREIGN' 'KEY' '(' column_list ')' references_clause
    | 'UNIQUE' '(' column_list ')'
    | 'CHECK' '(' expr ')'
    | 'EXCLUDE' '(' exclude_element (',' exclude_element)* ')' where_clause?
    ;

exclude_element: expr 'WITH' operator;

table_option
    : 'ENGINE' '=' identifier
    | 'CHARSET' '=' identifier
    | 'COLLATE' '=' identifier
    | 'COMMENT' '=' STRING_LITERAL
    ;


create_view_stmt
    : 'CREATE' ('OR' 'REPLACE')? 'VIEW' if_not_exists?
      view_name ('(' column_list ')')? 'AS' select_stmt
      ('WITH' ('CASCADE' | 'LOCAL')? 'CHECK' 'OPTION')?
    ;


create_index_stmt
    : 'CREATE' 'UNIQUE'? 'INDEX' if_not_exists?
      index_name 'ON' table_name '(' index_column_list ')'
      where_clause?
    ;

index_column_list: index_column (',' index_column)*;

index_column: column_name ('ASC' | 'DESC')?;


drop_stmt
    : 'DROP' drop_object if_exists? object_name ('CASCADE' | 'RESTRICT')?
    ;

drop_object: 'TABLE' | 'VIEW' | 'INDEX' | 'SCHEMA' | 'DATABASE';

if_exists: 'IF' 'EXISTS';


alter_table_stmt
    : 'ALTER' 'TABLE' table_name alter_table_action (',' alter_table_action)*
    ;

alter_table_action
    : 'ADD' 'COLUMN'? column_definition
    | 'DROP' 'COLUMN'? column_name ('CASCADE' | 'RESTRICT')?
    | 'ALTER' 'COLUMN'? column_name alter_column_action
    | 'ADD' table_constraint
    | 'DROP' 'CONSTRAINT' constraint_name ('CASCADE' | 'RESTRICT')?
    | 'RENAME' 'TO' new_table_name
    | 'RENAME' 'COLUMN'? column_name 'TO' new_column_name
    ;

alter_column_action
    : 'SET' 'DEFAULT' default_value
    | 'DROP' 'DEFAULT'
    | 'SET' 'NOT' 'NULL'
    | 'DROP' 'NOT' 'NULL'
    | 'TYPE' data_type
    ;


truncate_stmt: 'TRUNCATE' 'TABLE'? table_name;

explain_stmt: 'EXPLAIN' ('ANALYZE' | 'VERBOSE')* sql_stmt;

show_stmt
    : 'SHOW' 'TABLES'
    | 'SHOW' 'COLUMNS' 'FROM' table_name
    | 'SHOW' 'INDEX' 'FROM' table_name
    | 'SHOW' 'CREATE' 'TABLE' table_name
    ;

use_stmt: 'USE' database_name;


expr
    : literal                                      # LiteralExpr
    | column_reference                             # ColumnExpr
    | function_call                                # FunctionExpr
    | '(' expr ')'                                # ParenExpr
    | expr '[' expr ']'                           # ArrayAccessExpr
    | expr '.' identifier                         # MemberAccessExpr
    | ('+'|'-'|'NOT') expr                        # UnaryExpr
    | expr ('*'|'/'|'%') expr                     # MultiplicativeExpr
    | expr ('+'|'-'|'||') expr                    # AdditiveExpr
    | expr ('='|'!='|'<>'|'<'|'<='|'>'|'>='|'LIKE'|'ILIKE'|'SIMILAR TO'|'~'|'~*'|'!~'|'!~*') expr  # ComparisonExpr
    | expr 'IS' 'NOT'? ('NULL'|'TRUE'|'FALSE')    # IsExpr
    | expr 'IN' '(' (expr_list | select_stmt) ')' # InExpr
    | expr 'NOT'? 'BETWEEN' expr 'AND' expr       # BetweenExpr
    | expr 'AND' expr                             # AndExpr
    | expr 'OR' expr                              # OrExpr
    | 'EXISTS' '(' select_stmt ')'                # ExistsExpr
    | '(' select_stmt ')'                         # SubqueryExpr
    | 'CASE' case_when+ case_else? 'END'          # CaseExpr
    | window_function                             # WindowFunctionExpr
    | 'CAST' '(' expr 'AS' data_type ')'          # CastExpr
    | expr '::' data_type                         # PostgresCastExpr
    ;

case_when: 'WHEN' expr 'THEN' expr;
case_else: 'ELSE' expr;

column_reference
    : column_name
    | table_name '.' column_name
    | schema_name '.' table_name '.' column_name
    ;

function_call
    : function_name '(' (distinct_clause? expr_list | '*')? ')' filter_clause?
    ;

filter_clause: 'FILTER' '(' 'WHERE' expr ')';

window_function
    : function_name '(' expr_list? ')' 'OVER' window_specification
    | function_name '(' expr_list? ')' 'OVER' identifier
    ;


expr_list: expr (',' expr)*;

column_list: column_name (',' column_name)*;

literal
    : INTEGER                    # IntegerLiteral
    | DECIMAL                    # DecimalLiteral
    | STRING_LITERAL             # StringLiteral
    | 'TRUE' | 'FALSE'           # BooleanLiteral
    | 'NULL'                     # NullLiteral
    | DATE_LITERAL               # DateLiteral
    | TIME_LITERAL               # TimeLiteral
    | TIMESTAMP_LITERAL          # TimestampLiteral
    ;


table_name: qualified_name;
view_name: qualified_name;
index_name: identifier;
column_name: identifier;
table_alias: identifier;
column_alias: identifier;
function_name: identifier;
constraint_name: identifier;
new_table_name: identifier;
new_column_name: identifier;
database_name: identifier;
schema_name: identifier;
object_name: qualified_name;
operator: identifier;

qualified_name: identifier ('.' identifier)*;

identifier: IDENTIFIER | keyword;


keyword
    : 'SELECT' | 'FROM' | 'WHERE' | 'GROUP' | 'BY' | 'HAVING' | 'ORDER'
    | 'INSERT' | 'UPDATE' | 'DELETE' | 'CREATE' | 'DROP' | 'ALTER'
    | 'TABLE' | 'VIEW' | 'INDEX' | 'CONSTRAINT' | 'PRIMARY' | 'FOREIGN'
    | 'KEY' | 'UNIQUE' | 'NOT' | 'NULL' | 'DEFAULT' | 'CHECK'
    | 'INNER' | 'LEFT' | 'RIGHT' | 'FULL' | 'OUTER' | 'CROSS' | 'JOIN'
    | 'UNION' | 'INTERSECT' | 'EXCEPT' | 'ALL' | 'DISTINCT'
    | 'AS' | 'ON' | 'USING' | 'AND' | 'OR' | 'IN' | 'EXISTS' | 'BETWEEN'
    | 'LIKE' | 'ILIKE' | 'SIMILAR' | 'TO' | 'IS' | 'TRUE' | 'FALSE'
    | 'CASE' | 'WHEN' | 'THEN' | 'ELSE' | 'END' | 'CAST'
    ;






ASSIGN: '=';
EQ: '=' | '==';
NE: '<>' | '!=' | '~=' | '^=' | '¬=';
LT: '<';
LE: '<=';
GT: '>';
GE: '>=';
PLUS: '+';
MINUS: '-';
MULTIPLY: '*';
DIVIDE: '/';
MODULO: '%';
CONCAT: '||';


LPAREN: '(';
RPAREN: ')';
LBRACKET: '[';
RBRACKET: ']';
COMMA: ',';
SEMICOLON: ';';
DOT: '.';
COLON: ':';
DOUBLE_COLON: '::';


INTEGER: [0-9]+;
DECIMAL: [0-9]* '.' [0-9]+ | [0-9]+ '.' [0-9]*;


STRING_LITERAL
    : '\'' (~['\r\n] | '\'\'')*? '\''
    | '"' (~["\r\n] | '""')*? '"'
    | '$$' .*? '$$'
    ;


DATE_LITERAL: 'DATE' WS* STRING_LITERAL;
TIME_LITERAL: 'TIME' WS* STRING_LITERAL;
TIMESTAMP_LITERAL: 'TIMESTAMP' WS* STRING_LITERAL;


IDENTIFIER
    : [a-zA-Z_] [a-zA-Z0-9_$]*
    | '`' (~[`\r\n])+ '`'
    | '"' (~["\r\n])+ '"'
    ;


WS: [ \t\r\n]+ -> skip;

LINE_COMMENT
    : '--' ~[\r\n]* -> skip
    ;

BLOCK_COMMENT
    : '/*' .*? '*/' -> skip
    ;


UNKNOWN_CHAR: .