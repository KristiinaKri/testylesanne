function getEl(id) {
	var element = document.getElementById(id);
	return element;
}

/**
*	If player spins again, the reels will continue to move from these positions,
*	which they obtained by the end of the previous spin.
*/
var tempPos11 = 0;
var tempPos12 = -605;
var tempPos21 = 0;
var tempPos22 = -605;
var tempPos31 = 0;
var tempPos32 = -605;

var images = [ "Reel/3xBAR.png",
				"Reel/BAR.png",
				"Reel/2xBAR.png",
				"Reel/7.png",
				"Reel/Cherry.png" ];

var reelIds = [ "reel1", "reel2", "reel3" ];

var winlineIds = [ "winline-top-left", "winline-top-right",
					"winline-center-left", "winline-center-right",
					"winline-bottom-left", "winline-bottom-right" ];

/**
*	If player refreshes the page, reels will be reset to their original positions
*	and player's balance will be 0.
*/
window.onload = function() {
	fillReel("images1-1"), fillReel("images1-2"),
	fillReel("images2-1"), fillReel("images2-2"),
	fillReel("images3-1"), fillReel("images3-2"),
	emptyBalance()
};

function fillReel(reel) {
	for (var i = 0; i < images.length; i++) {
		var image = document.createElement("IMG");
		image.setAttribute("src", images[i]);
		getEl(reel).appendChild(image);
	}
}

function emptyBalance() {
	getEl("balance").value = 0;
}

/**
*	If player clicks "S P I N":
*	1. Check balance
*	2. In case of win during previous spin, return colors to default
*	3. Do not allow to click buttons or change balance
*	4. Spin reels
*	5. Check for winning lines
*/
function checkSpin() {
	if (getEl("balance").value < 1) {
		console.log("Out of money! Please insert coin(s)!");
	} else {
		getEl("balance").value--;
		returnColor();
		getEl("spin").disabled = true;
		getEl("balance").disabled = true;
		getEl("debug").disabled = true;
		spin(getEl("images1-1"), getEl("images1-2"), 2000);
		spin(getEl("images2-1"), getEl("images2-2"), 2500);
		spin(getEl("images3-1"), getEl("images3-2"), 3000);
		setTimeout(checkWin, 3001);
	}
}

function returnColor() {
	for (var i = 0; i < reelIds.length; i++) {
		getEl(reelIds[i]).style.borderColor = "#DDDDDD";
	}
	for (var j = 0; j < winlineIds.length; j++) {
		getEl(winlineIds[j]).style.borderBottomColor = "#DDDDDD";
	}
}

/**
*	This is where the spinning animation happens.
*/
function spin(reel1, reel2, timeout) {
	var seconds = 0;
	var timer = setInterval(setTime, 500);	// For counting the elapsed spin time
	function setTime() {
		seconds += 500;
	}
	var pos1 = 0;
	var pos2 = -605;
	if (timeout == 3000) {			// In case of previous spin,
		pos1 = tempPos31;			// assign old positions
		pos2 = tempPos32;			// so spinning continues from there.
	} else if (timeout == 2500) {
		pos1 = tempPos21;
		pos2 = tempPos22;
	} else if (timeout == 2000) {
		pos1 = tempPos11;
		pos2 = tempPos12;
	}
	var time = Math.floor((Math.random() * 10) + 1) * 10;	// Spinning speed is random
	var interval = setInterval(frame, time);
	function frame() {
		if (seconds == timeout) {
			clearInterval(interval);
			while (pos1 != 0 && pos1 != 60 && pos1 != 121 &&
					pos1 != 181 && pos1 != 242 && pos1 != 302 &&
					pos1 != 363 && pos1 != 423 && pos1 != 484 &&
					pos1 != 544 && pos1 != 605) {		// Budge reel until it is aligned
				pos1++;
				pos2++;
				reel1.style.top = pos1 + "px";
				reel2.style.top = pos2 + "px";
			}
			if (timeout == 3000) {						// When last reel has stopped,
				getEl("spin").disabled = false;			// allow to click buttons
				getEl("balance").disabled = false;		// and change balance.
				getEl("debug").disabled = false;
				tempPos31 = pos1;
				tempPos32 = pos2;
			} else if (timeout == 2500) {
				tempPos21 = pos1;
				tempPos22 = pos2;
			} else if (timeout == 2000) {
				tempPos11 = pos1;
				tempPos12 = pos2;
			}
		} else {									// If one div column with images reaches top,
			if (pos2 == 0) {						// immediately add lower one above it
				pos1 = -605;						// so they would make an illusion
				reel1.style.top = pos1 + "px";		// of a continuous spin.
			} else if (pos1 == 0) {
				pos2 = -605;
				reel2.style.top = pos2 + "px";
			}
			pos1++;
			pos2++;
			reel1.style.top = pos1 + "px";
			reel2.style.top = pos2 + "px";
			requestAnimationFrame(frame);
		}
	}
}

/**
*	This is where the verification of any winning lines SHOULD happen.
*/
function checkWin() {
	if ((tempPos11 == 0 && tempPos21 == 0 && tempPos31 == 0) ||
		(tempPos11 == 0 && tempPos22 == 0 && tempPos31 == 0) ||
		(tempPos11 == 0 && tempPos21 == 0 && tempPos32 == 0) ||
		(tempPos12 == 0 && tempPos21 == 0 && tempPos31 == 0) ||
		(tempPos12 == 0 && tempPos22 == 0 && tempPos31 == 0) ||
		(tempPos12 == 0 && tempPos21 == 0 && tempPos32 == 0) ||
		(tempPos11 == 0 && tempPos22 == 0 && tempPos32 == 0) ||
		(tempPos12 == 0 && tempPos22 == 0 && tempPos32 == 0)) {
		getEl("balance").value = +getEl("balance").value + +60;
		getEl("reel1").style.borderColor = "#FF0000";
		getEl("reel2").style.borderColor = "#FF0000";
		getEl("reel3").style.borderColor = "#FF0000";
		getEl("winline-top-left").style.borderBottomColor = "#FF0000";
		getEl("winline-top-right").style.borderBottomColor = "#FF0000";
		getEl("winline-bottom-left").style.borderBottomColor = "#FF0000";
		getEl("winline-bottom-right").style.borderBottomColor = "#FF0000";
	} else if ((tempPos11 == -121 && tempPos21 == -121 && tempPos31 == -121) ||
				(tempPos12 == -121 && tempPos22 == -121 && tempPos32 == -121)) {
		getEl("balance").value = +getEl("balance").value + +4000;
		getEl("reel1").style.borderColor = "#FF0000";
		getEl("reel2").style.borderColor = "#FF0000";
		getEl("reel3").style.borderColor = "#FF0000";
		getEl("winline-bottom-left").style.borderBottomColor = "#FF0000";
		getEl("winline-bottom-right").style.borderBottomColor = "#FF0000";
	}
}