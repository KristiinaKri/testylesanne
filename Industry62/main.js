var app = angular.module("sportsApp", []);

app.controller("athletesCtrl", function($scope) {
	$scope.athletes = [
		{
			Flag: "http://www.senojflags.com/images/country-flag-icons/Japan-Flag.png",
			Country: "JPN",
			Name: "Akihiko Nakamura",
			Time: "4:18.370",
			Points: "823"
		},
		{
			Flag: "http://www.senojflags.com/images/country-flag-icons/Australia-Flag.png",
			Country: "AUS",
			Name: "Cedric Dubler",
			Time: "4:32.120",
			Points: "731"
		},
		{
			Flag: "http://www.senojflags.com/images/country-flag-icons/Austria-Flag.png",
			Country: "AUT",
			Name: "Dominik Distelberger",
			Time: "4:33.470",
			Points: "722"
		},
		{
			Flag: "http://www.senojflags.com/images/country-flag-icons/Czech-Republic-Flag.png",
			Country: "CZE",
			Name: "Jiri Sykora",
			Time: "Did not finish",
			Points: "0"
		},
		{
			Flag: "http://www.senojflags.com/images/country-flag-icons/Estonia-Flag.png",
			Country: "EST",
			Name: "Karl Robert Saluri",
			Time: "4:39.400",
			Points: "684"
		},
		{
			Flag: "http://www.senojflags.com/images/country-flag-icons/Poland-Flag.png",
			Country: "POL",
			Name: "Pawel Wiesiolek",
			Time: "4:42.270",
			Points: "666"
		}
	];
	$scope.column = "Time";				// default order by
	$scope.reverse = false;
	$scope.sortBy = function(col) {		// order by
		$scope.column = col;
		if ($scope.reverse) {
			$scope.reverse = false;
			$scope.reverseclass = "arrow-up";
		} else {
			$scope.reverse = true;
			$scope.reverseclass = "arrow-down";
		}
	};
	$scope.sortClass = function(col) {	// show arrow
		if ($scope.column == col ) {
			if ($scope.reverse) {
				return "arrow-down";
			} else {
				return "arrow-up";
			}
		} else {
			return "";
		}
	};
});