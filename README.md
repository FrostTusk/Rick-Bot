# Rick-Bot
Discord exploitation testing bot designed to attack the [Morty Discord Bot](https://github.com/Wh1spr/Morty).

## Theory
This bot was trying to attack a specific vulnerability in the [Morty Discord Bot](https://github.com/Wh1spr/Morty).
The idea was to overwrite the original message id string, which is used by Morty in database queries, with an SQL injection.
If Rick worked, Rick would be able to change the permissions of all the users on every server Morty is managing.

## Execution
In practice, Rick doesn't work. This is due to the fact that the [Java Discord Api](https://github.com/DV8FromTheWorld/JDA) makes sure that message id strings cannot be spoofed or overwritten.
Despite the fact that Rick-Bot doesn't work, it's still a useful exploitation base to build upon if the Morty Bot is ever expanded upon.
