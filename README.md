# frogwhat
This is the Frogcomposband Grand Outfit What-if-ulator, or "frogwhat" for short. The general idea is this:
1. The top textbox is your list of equipment. The default text demonstrates the item formatting: four-letter slot name, item name, and comma-separated enchantments between curly braces. (You'll need to replace the example text with your actual gear, of course.)
2. The middle two textboxes are your list of desired enchantments and your available slots. The default text might be good enough for you, but feel free to change it as needed. (Change SHLD to a second WEPN slot if you're dual-wielding, just make everything BODY or whatever for a Vortex or Jelly, etc.)
3. After you input your available gear and adjust those two lists, you click "Search", which will create two lists of items: one on the left based on "quality value", and one on the right based on "slot relevance".
* Quality value: Simply how many desired qualities are provided by each item.
* Slot relevance: Basically how limited your slot options are for a particular quality. For example, if you want Resist Shards and the only items you have with it are weapons, then a sword with Resist Shards would have 100% slot relevance. If you also had some rings with Resist Shards, then any weapon or ring with Resist Shards would have at least 50% slot relevance, and so on.
4. The text box at the bottom of the screen is your "outfit". You can click an item in either list to quickly add or remove it from the outfit box. Then, if you click "Search" again, your outfit qualities will be subtracted from your desired qualities, with the result displayed in the "unmatched qualities" textbox above. (I was careful about this: you can, for example, specify "Po" twice, and then if you only have one Resist Poison item equipped others will still appear in the lists.)
5. You can save your items and/or outfit to plaintext files and load them again later. Your desired effects and available equipment slots will be stored in the items list file for your convenience.
6. Although it was designed for frogcomposband, this is actually a general-purpose tool which can help with other games, and presumably even with non-games.

&nbsp;

Changelog:
* 0.4a: First fully-functional version.
* 0.5a: Added equipment slot functionality.
* 0.6a: Added default/demo text to make the interface very, very slightly more user-friendly.
* 0.7a: First attempt at implementing the "slot relevance" table.
* 0.7.1a: Fixed the program crashing if you chose anything other than sword&board, and included a shield in the demo item list.
* 0.7.2a: Restricted slot relevance to desired qualities, and also fixed another crash.
* 0.8a: Added slot list customization.
* 0.9a: The desired enchantment and slot lists are now stored as (and retrieved from) the first two lines of the item list file.
* 0.9.1b: Rearranged the buttons into a more intuitive layout. First public release.

&nbsp;

Future goals:
* Make sure the "slot relevance" list is working properly.
* Automatically convert, e.g. "{AcElCo}" to "{Ac,El,Co}", to accomodate copy/pasted text.
* Optionally apply color-coding to the lists so you can see at a glance how good the items are.
* Maybe improve the way slot names work? They're hardcoded to four characters right now.

&nbsp;

Credits:
* SWT: https://www.eclipse.org/swt/
* entriesSortedByValues(): https://stackoverflow.com/a/11648106
