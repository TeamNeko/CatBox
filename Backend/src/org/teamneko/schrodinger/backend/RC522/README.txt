README

'''''''''''''''''''''''
'''''''''''''''''''''''
'''''''''''''''''''''''
'''''''''''''''''''''''
'''''',      ''''; ''''
'''''     ', '''', ''''
'''',    '', :'''  ''''
''''    `'';       ''''
''''    `'''      '''''
'''',    ''''    :'''''
'''''     '''''''''''''
'''''',         ,''''''
'''''''''''''     '''''
''''':    ''''    ,''''
'''''      '''`    ''''
''''       ;''`    ''''
''''  ''': ,''    ,''''
'''' ,'''' ,'     '''''
'''' ;''''      ,''''''
'''''''''''''''''''''''
'''''''''''''''''''''''
'''''''''''''''''''''''
'''''''''''''''''''''''

University of Sherbrooke Autumn 2016

To connect the MFRC522 sensor to the Raspberry Pi in it's actual configuration.
You can change the RST pin if you want in it's initialisation. See MFRC522.java
 

MFRC522

SDA	24
SCK	23
MOSI	19
MISO	21
IRQ	NC
GND	GND
RST	22
3.3V	3V3



Whenever you use the library, the function ReadID returns a boolean if the lecture has occured and was correct.
If it fails at the initialisation, belive me it will tell you in the console.

To get the ID read, use the member variable of the class "currentID"
If it has read something, a good ID will be there (10digits, formated in a string format)
Otherwise, it will be full of zeros.

