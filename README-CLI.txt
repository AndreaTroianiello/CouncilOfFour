Once changed the turn the current player has 60 seconds to make an action(chat is considered an action).
If the player hasn't sent anything, the view get suspended.
If the player is suspended, his turns get skipped.
If all the player are suspended, the game ends.
The player can come back in the game when he sends something(even a refused action or a chat message),
if the game isn't ended yet.

Start game from CLI: Client.java (package it.polimi.ingsw.cg23.client.cli)
CLI's commands:

HELP

QUIT

SOCKET address
RMI address

CREATION userName nameMap(map*number* 1<=number<=8, example. map1)
When someone starts a connection with the server he must creates his player by inserting his username and a 
preference for the map(his map will be chosen if he is the first player created and the map is found)

ADDITIONAL(secondary action)

BUILDKING cityName
The name of the city must be written with the first letter upper case and the rest of the name lowercase.

BUILDTILE numberTile cityName
numberTile goes from 0 to the number of the available tiles minus one. The tiles are sorted from the first one
acquired to the last one. Them can be showed by the command SHOW TILES.
The name of the city must be written with the first letter upper case and the rest of the name lowercase.

BUYTILE regionName numberTile
The name of the region must be written with the first letter upper case and the rest of the name lowercase.
numberTile is 0(the tiles under the region on the left on the board) or 1(the one on the right)

CHANGE regionName
The name of the region must be written with the first letter upper case and the rest of the name lowercase.

ELECT regionName colorName
The name of the region must be written with the first letter upper case and the rest of the name lowercase.
The name of the color must be written with the first letter upper case and the rest of the name lowercase and
must be chosen between the color present in the councils and in the councillor pool.

ELECT KING colorName
The name of the color must be written with the first letter upper case and the rest of the name lowercase and
must be chosen between the color present in the councils and in the councillor pool.

ELECTASSISTANT KING colorName
The name of the color must be written with the first letter upper case and the rest of the name lowercase and
must be chosen between the color present in the councils and in the councillor pool.

ELECTASSISTANT regionName colorName
The name of the region must be written with the first letter upper case and the rest of the name lowercase.
The name of the color must be written with the first letter upper case and the rest of the name lowercase and
must be chosen between the color present in the councils and in the councillor pool.

HIRE

ENDTURN
It must be used at the end of the turn, and at the and of both the market's phases 

SHOW BOARD
SHOW HAND
SHOW TILES
SHOW NEIGHBORS nameCity
The name of the city must be written with the first letter upper case and the rest of the name lowercase.


MARKET BUY numberItem
numberItem goes from 0 to the number of item in the market minus 1. The items are sorted by inserting order, 
and can be showed with the command MARKET VIEW(the top one is the first inserted)

MARKET SELL TILE/CARD/ASSISTANTS numberTile/numberCard/numberAssistants price

MARKET VIEW


BONUS MODE:

ESCBONUS 
esce dalla modalità bonus(non lo effettua).
