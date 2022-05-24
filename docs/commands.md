# Commands

`COMMAND-NAME PARAMETER-PATTERN`

```
LOOP CONST,<NUMBER>;
LOOP END;

LOOP IN,{<VAR_ARRAY>};
LOOP END;

CLICK;

// COMKEY is for combination of key clicks
// Supported keys are in the ComKey.java::init
COMKEY <DELAY>,<KEY>[,<KEY>, ...]
COMKEY <DELAY>,1234 // will press 1234

DELAY <Millisecond>;

ARR <VARNAME>=1,2,3,4;
{ARR_VARNAME}
{ARR_VARNAME[{CURR_ROUND}]}

VAR <VARNAME>=1;
Default variable
    CURR_ROUND

MOVE <X>,<Y>;

PAST {<VARNAME>};
PAST <STRING>;

PENDBYRGB <R>,<G>,<B>,<X>,<Y>,<IsEqual>,<PollingTime>;
```
