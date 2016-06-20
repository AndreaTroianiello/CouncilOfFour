Once changed the turn the current player has 60 seconds to make an action(chat is considered an action).
If the player hasn't sent anything, the view get suspended.
If the player is suspended, his turns get skipped.
If all the player are suspended, the game ends.
The player can come back in the game when he sends something(even a refused action or a chat message),
if the game isn't ended yet.

Start game from CLI: Client.java (package it.polimi.ingsw.cg23.client.cli)
CLI's commands:

SOCKET address
RMI address

CREATION userName nameMap (ex. map1,map2,map3,...)
ADDITIONAL
BUILDKING cityName
BUILDTILE numberTile cityName
BUYTILE regionName numberTile
CHANGE regionName
ELECT regionName colorName
ELECT KING colorName
ELECTASSISTANT KING colorName
ELECTASSISTANT regionName colorName
HIRE
ENDTURN

SHOW BOARD
SHOW HAND
SHOW TILES
SHOW NEIGHBORS nameCity

MARKET BUY numberItem
MARKET SELL TILE/CARD/ASSISTANTS numberTile/numberCard/numberAssistants price
MARKET VIEW