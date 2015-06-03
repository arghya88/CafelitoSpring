var coffeeApp = angular.module('coffeeApp', ['ngResource', 'ui.bootstrap']);


coffeeApp.factory('CoffeeShopLocator', function ($resource) {
    return $resource('/coffeeshop/nearest',
        {latitude: '@latitude', longitude: '@longitude'}, {});
});

coffeeApp.controller('CoffeeShopController', function ($scope, $window, CoffeeShopLocator) {
    $scope.findCoffeeShopNearestToMe = function () {
        window.navigator.geolocation.getCurrentPosition(function (position) {
            $scope.getCoffeeShopAt(position.coords.latitude, position.coords.longitude)
        }, null);
    };
    $scope.getCoffeeShopAt = function (latitude, longitude) {
        CoffeeShopLocator.get({latitude: latitude, longitude: longitude}).$promise
            .then(
            function (value) {
                $scope.nearestCoffeeShop = value;
            })
            .catch(
            function (value) {
                //default coffee shop
                $scope.getCoffeeShopAt(12.9350891,77.6134262);
            });
    };
});

coffeeApp.factory('CoffeeOrder', function ($resource) {
    return $resource('/coffeeshop/order/', {}
    );
});

coffeeApp.controller('OrderController', function ($scope, CoffeeOrder) {
    $scope.types = [
        {name: 'Americano', family: 'Coffee'},
        {name: 'Latte', family: 'Coffee'},
        {name: 'Tea', family: 'that other drink'},
        {name: 'Cappuccino', family: 'Coffee'}
    ]
    $scope.sizes = ['Small', 'Medium', 'Large']
    $scope.messages = [];

    $scope.giveMeCoffee = function () {
        CoffeeOrder.save($scope.drink,
            function (order) {
                $scope.messages.push({type: 'success', msg: 'Order sent!'})
            }
        )
    }

    $scope.closeAlert = function (index) {
        $scope.messages.splice(index, 1);
    };

})