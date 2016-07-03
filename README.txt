Start the server from Server.java(package it.polimi.ingswcg23.server)
Start game from CLI: Client.java (package it.polimi.ingsw.cg23.client.cli)
Start game from GUI from HomeFrame.java(package it.polimi.ingsw.cg23.client.gui)

CLI instructions: CLI.txt
GUI instructions: GUI.txt

Once changed the turn the current player has 60 seconds to make an action(chat is considered an action).
If the player hasn't sent anything, the view get suspended.
If the player is suspended, his turns get skipped.
If all the player are suspended, the game ends.
The player can come back in the game when he sends something(even a refused action or a chat message),
if the game isn't ended yet.

