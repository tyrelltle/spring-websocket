var app = angular.module('app', [
    'ngRoute',
    'ui.bootstrap'
]);
app.config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');

        $routeProvider
            .when('/', {
                templateUrl: '/home.html'
            });
    }]);


app.controller('Ctrl', function ListController($scope,$http,$uibModal) {
    $scope.page={subscribers:[]};

    $scope.refresh=function(){
        $http({
            method: 'GET',
            url: "api/subscribers"
        }).then(
            function(res) {
                console.log(res);
                $scope.page.subscribers=res.data;
            },
            function(err) {
                console.log(err);
                alert('failed to get subscriber list ');
            }
        );
    }

    $scope.refresh();
    $scope.addSubscriber=function(){
        var modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'addSubscriber.html',
            controller: 'AddSubscriberCtrl',
            size: 'lg'
        });
        modalInstance.result.then(function success(){
            $scope.refresh();
        });
    }

    $scope.addNewTemp=function(){
        var mode=$('#temp_mode_select').val();
        var temp_val=$scope.page.newTempValue+' '+mode;
        $http({
            method: 'POST',
            url: "api/temperature",
            data: [{name:temp_val}]
        }).then(
            function(res) {
                $scope.refresh();
            },
            function(err) {
                console.log(err);
                alert(err.data.message);
            }
        );

    }
});

app.controller('AddSubscriberCtrl', function ListController($scope,$http,$uibModalInstance) {
    $scope.doc={thresholds:[]};
    $scope.newthres={};
    $scope.addthres=function(){
        var ok=true;
        $scope.doc.thresholds.forEach(function(t){
            if(t.name===$scope.newthres.name){
                alert('duplicate threshold name');
                ok=false;
            }
        });
        if(!ok){
            return;
        }


        if(isNaN($scope.newthres.value)){
            alert('enter float number in value filed');
            return;
        }

        if(isNaN($scope.newthres.fluctuation)){
            alert('enter float number in fluctuation filed');
            return;
        }

        $scope.newthres.direction=$('#direction_select').val();
        $scope.doc.thresholds.push($scope.newthres);
        $scope.newthres={};
    }

    $scope.create=function(){
        if($scope.doc.thresholds.length==0){
            alert('please add thresholds. remember click on Add Threshold Button after you have filled in all the threshold fields');
            return;
        }
        $http({
            method: 'POST',
            url: "api/subscribers",
            data: $scope.doc
        }).then(
                function(res) {
                    $uibModalInstance.close();
                },
                function(err) {
                    console.log(err);
                    alert(err.data.message);
                }
            );
    }
})