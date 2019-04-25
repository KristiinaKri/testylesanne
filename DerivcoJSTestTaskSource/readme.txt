The "Slot machine 1.0" is a nearly-completed slot machine simulation web-app, which should run properly under both Google Chrome and Firefox.

During these 3 days, I managed to design the UI within the first day, leaving 2 days to deal with the more complicated JavaScript part. When it came to the reels (as well as the slot machine in general), I tried to be pixel-perfect and figure out the exact measurements.

During those 2 days, I spent most of my time trying to figure out how to get the animation to work properly by going through some examples on the Internet. Until I had an idea to make 2 divs, which are filled with the reel images, rotate around each other continuously.

Because of this, I did not manage to figure out how to detect winning combinations in those 3 lines. Nor did I manage to make the winning sum blink, though I did show how the reels and win-lines would turn red in case of a winning combination.

I also did not manage to design the debug area. My idea was to add a hidden <div> element, which would appear underneath the combination divs when clicking on the "Debug" button. The default mode is random, which I have implemented successfully.