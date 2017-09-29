<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title></title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/angular.js/1.4.3/angular.min.js"></script>
    <script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/angular-ui-bootstrap/0.13.2/ui-bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/angular-ui-bootstrap/0.13.2/ui-bootstrap-tpls.min.js"></script>
    <link href="//cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.min.css" rel="stylesheet">
    <script src="/spider-web/js/bootstrap-datepicker.js"></script>
    <style>
        .chosen {
            background-color: gold;
        }

        body {
            padding-top: 70px;
            margin: 0 auto;
        }

        .form-center {
            text-align: center;
            padding-top: 10px;
        }
    </style>
</head>

<body ng-app="oddsApp" class="container-fluid" ng-controller="oddsController">
<div class="navbar navbar-default navbar-fixed-top">
    <div class="form-inline form-center">
        <div class="form-group">
            <label for="exampleInputName2">Search</label>
            <input type="text" class="form-control" id="exampleInputName2" ng-model="query">
            <button type="button" class="btn btn-primary" ng-click="selectAll()">Select All</button>
            <button type="button" class="btn btn-primary" ng-click="deselectAll()">Deselect All</button>
            <label for="startDate">Start Date</label>
            <input class="datepicker" type="text" data-date-format="mm/dd/yyyy" readonly="true" id="startDate">
            <label for="endDate">End Date</label>
            <input class="datepicker" type="text" data-date-format="mm/dd/yyyy" readonly="true" id="endDate">
            <button type="button" class="btn btn-success" ng-click="refresh()" id="refreshBtn">Refresh</button>
            <button type="button" class="btn btn-info" ng-click="exportExcel()">Export Excel</button>
        </div>
    </div>
</div>
<div class="col-md-2">
    <jsp:include page="navigation.jsp"/>
</div>
<div class="col-md-10">
    <div class="alert alert-success" role="alert" ng-model="total">共查出比赛{{total}}条，单击条目任意区域可以选取，比赛已按match
        code排序，在search框中输入match code可进行筛选，
        <a class="btn btn-info btn-xs" ng-click="oneKeySelect()" ng-bind="oneKeySelectLabel" role="button"></a> 未导入比赛
    </div>
    <div ng-show="loading">正在加载数据...</div>
    <ul class="list-group">
        <li class="list-group-item" ng-repeat="odds in oddsList | filter:filterMatchCode | orderBy:orderProp">
            <h3>
               <span class="label label-info">
                    {{odds.matchCode}} |  {{odds.matchLeague}} | {{odds.basketballSportteryEntity.homeTeam}} VS {{odds.basketballSportteryEntity.awayTeam}} 
                </span>
                <span class="label label-danger">{{odds.absenceState}}</span>
              
                <button type="button" class="btn btn-success btn-sm" ng-click="refreshOdds(odds)"
                        id="refreshOddsBtn{{odds.id}}">Refresh
                </button>
               <!--  <button type="button" class="btn btn-success btn-sm" ng-click="calcSupAndTtg(odds)"
                        id="calcSupAndTtg{{odds.id}}">Calc SUP/TTG
                </button> -->
                <span class="label label-info" id="supAndTtg{{odds.id}}"></span>
            </h3>
            <table class="table table-bordered" ng-class='{chosen: odds.choose}' ng-click="chooseOdds(odds)"
                   id="{{odds.id}}">
                <tr>
                    <th ng-class="{different: odds.different}">HDC</th>
                    <th ng-class="{different: odds.different}">HILO</th>
                
                </tr>
                <tr>
                    <td>
                        <table class="table">
                            <tr>
                                <th>#</th>
                                <th>官</th>
                                <th>bet365</th>
                                <th>Crown</th>
                             
                            </tr>
                            <tr>
                                <td>Home</td>
                              	<td>{{odds.sportteryHdcHome}}</td>
                              	<td>{{odds.bet365HdcHome}}</td>
                              	<td>{{odds.crownHdcHome}}</td>
                              
                            </tr>
                            <tr>
                                <td>Line</td>
                            	<td>{{odds.sportteryHdcLines}}</td>
                              	<td>{{odds.bet365HdcLines}}</td>
                              	<td>{{odds.crownHdcLines}}</td>
                            </tr>
                            <tr>
                                <td>Away</td>
                             	<td>{{odds.sportteryHdcAway}}</td>
                              	<td>{{odds.bet365HdcAway}}</td>
                              	<td>{{odds.crownHdcAway}}</td>
                            </tr>
                            <tr>
                                <td>M</td>
                            	<td>{{odds.sportteryHdcMargin}}</td>
                              	<td>{{odds.bet365HdcMargin}}</td>
                              	<td>{{odds.crownHdcMargin}}</td>
                            </tr>
                            <tr>
                                <td>U</td>
                            	<td>{{odds.sportteryHdcUpdate}}</td>
                              	<td>{{odds.bet365HdcUpdate}}</td>
                              	<td>{{odds.crownHdcUpdate}}</td>
                              	
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table class="table">
                            <tr>
                               <th>#</th>
                                <th>官</th>
                                <th>bet365</th>
                                <th>Crown</th>
                            </tr>
                            <tr>
                                <td>High</td>
                            	<td>{{odds.sportteryHiloHigh}}</td>
                            	<td>{{odds.bet365HiloHigh}}</td>
                            	<td>{{odds.crownHiloHigh}}</td>
                            </tr>
                            <tr>
                                <td>Line</td>
                               	<td>{{odds.sportteryHiloLines}}</td>
                            	<td>{{odds.bet365HiloLines}}</td>
                            	<td>{{odds.crownHiloLines}}</td>
                            </tr>
                            <tr>
                                <td>Low</td>
                               	<td>{{odds.sportteryHiloLow}}</td>
                            	<td>{{odds.bet365HiloLow}}</td>
                            	<td>{{odds.crownHiloLow}}</td>
                            </tr>
                            <tr>
                                <td>M</td>
                              	<td>{{odds.sportteryHiloMargin}}</td>
                            	<td>{{odds.bet365HiloMargin}}</td>
                            	<td>{{odds.crownHiloMargin}}</td>
                            </tr>
                            <tr>
                                <td>U</td>
                              	<td>{{odds.sportteryHiloUpdate}}</td>
                            	<td>{{odds.bet365HiloUpdate}}</td>
                            	<td>{{odds.crownHiloUpdate}}</td>
                            </tr>
                        </table>
                    </td>
                    
                </tr>
            </table>
        </li>
    </ul>
</div>
</body>

</html>
 <script src="/spider-web/js/my-angular-config.js"></script> 

 <script>
    $(function () {
        $('.datepicker').datepicker({
            autoclose: true
        });
    });
    var app = angular.module("oddsApp", []);
    MY_ANGULAR_CONFIG(app);
    app.controller("oddsController", function ($scope, $http) {
        $scope.oddsList = [];
        $scope.loading = true;
        $scope.startDate = null;
        $scope.endDate = null;
        $scope.filterMatchCode = function (element) {
            if ($.trim($scope.query) == '') {
                return true;
            }
            return element.matchCode.indexOf($scope.query) != -1 ? true : false;
        };
        $scope.logout = function () {
            $http.post('logout');
        }
        $scope.defaultSeconds = 30;
        $scope.seconds = $scope.defaultSeconds;
      
      	
      	
        setInterval(function () {
            $('#refreshBtn').text('Refresh-' + --$scope.seconds);
            if ($scope.seconds == 0) {

                $scope.$apply(firstLoad);
                $scope.seconds = $scope.defaultSeconds;
            }
        }, 1000); 
        
        
        
        
        
        
        var firstLoad = function () {
        
            $scope.loading = true;
            $("#refreshBtn").attr("disabled", true);
            
            $http.get('listBasketballOdds.do?startDate=' + $("#startDate").val() + "&endDate=" + $("#endDate").val())
                    .success(function (response) {
                        $.each(response, function (i, value) {
                 
                            if (value.absenceState == "1") {
                                value.absenceState = "已导入";
                            } else {
                                value.absenceState = "";
                            }
                     
                        });
                        $scope.oddsList = response;
                        $scope.total = $scope.oddsList.length;
                        $("#refreshBtn").attr("disabled", false);
                        $scope.loading = false;
                        $scope.seconds = $scope.defaultSeconds;
                    });
        }; 
        
        
        firstLoad();
        
        
       
    
         
        
        
      
        $scope.chooseOdds = function (odds) {
            if (odds.choose) {
                odds.choose = false;
              
            } else {
                odds.choose = true;
               
            }
        }
        
        $scope.selectAll = function () {
            $.each($scope.oddsList, function (i, value) {
                value.choose = true;
            });
        }
        $scope.deselectAll = function () {
            $.each($scope.oddsList, function (i, value) {
                value.choose = false;
            });
        }
        $scope.oneKeySelectLabel = "一键选中";
        $scope.oneKeySelect = function () {
            if (!$scope.oneKeySelected) {
                $scope.oneKeySelected = true;
                $scope.oneKeySelectLabel = "取消选中";
                $.each($scope.oddsList, function (i, value) {
                    if (value.choose) {
                        value.choose = false;
                    }
                    if (value.absenceState == "") {
                        $scope.chooseOdds(value);
                    }
                });
            } else {
                $scope.oneKeySelected = false;
                $scope.oneKeySelectLabel = "一键选中";
                $.each($scope.oddsList, function (i, value) {
                    if (value.choose == true) {
                        $scope.deselectAll();
                    }
                });
            }
        }
       
       
        $scope.orderProp = 'matchCode';
     
        $scope.refresh = function () {
            if ($scope.startDate == null || $scope.endDate == null) {
                if (!$scope.validate($("#startDate").val(), $("#endDate").val())) {
                    return;
                }
            }
            $scope.startDate = $("#startDate").val();
            $scope.endDate = $("#endDate").val();
           
            
            $("#refreshBtn").attr("disabled", true);
            $scope.loading = true;
            $http.get('listBasketballOdds.do?startDate=' + $("#startDate").val() + "&endDate=" + $("#endDate").val())
                    .success(function (response) {
                        $.each(response, function (i, value) {
                            if (value.absenceState == "1") {
                                value.absenceState = "已导入";
                            } else {
                                value.absenceState = "";
                            }
                    
                        });
                        
                        $scope.oddsList = response;
                        
                        
                        $scope.total = response.length;
                        $("#refreshBtn").attr("disabled", false);
                        $scope.loading = false;
                    });
            $scope.seconds = $scope.defaultSeconds;
        }
     
	 /* 刷新refresh */
        $scope.refreshOdds = function (odds) {
        
            $("#refreshOddsBtn" + odds.id).attr("disabled", true);
            $http.get('refreshOdds.do?matchCode=' + odds.matchCode + '&absenceState=' + odds.absenceState)
                    .success(function (response) {
                        if (response.absenceState == "1") {
                            response.absenceState = "已导入";
                        } else {
                            response.absenceState = "";
                        }
                        for (var i = 0; i < $scope.oddsList.length; i++) {
                            if (response.matchCode == $scope.oddsList[i].matchCode) {
                                $scope.oddsList[i] = response;
                            }
                        }
                        $("#refreshOddsBtn" + odds.id).attr("disabled", false);
                    });
        }
       /*  $scope.calcSupAndTtg = function (odds) {
            $("#calcSupAndTtg" + odds.id).attr("disabled", true);
            $.ajax({
                url: 'calcSupAndTtg.do',
                data: {'oddsModel': JSON.stringify(odds)},
                type: 'get',
                cache: false,
                dataType: 'json',
                success: function (data) {
                    $("#calcSupAndTtg" + odds.id).attr("disabled", false);
                    $("#supAndTtg" + odds.id).text('');
                    $("#supAndTtg" + odds.id).text('ljSUP: ' + data.liji.sup + ', ljTTG: ' + data.liji.ttg + ' jbbSUP: ' + data.jinbaobo.sup + ', jbbTTG: ' + data.jinbaobo.ttg);
                },
                error: function (XMLHttpRequest, txtStatus, errorThrown) {
                    $("#calcSupAndTtg" + odds.id).attr("disabled", false);
                    alert("失败！");
                }
            });
        }; */
        $scope.exportExcel = function () {
            var data = [];
            var match = {};
            var oddsModelHead = {};
            var basketballSportteryEntity = {};
           
            oddsModelHead.matchCode = "match_code";
            oddsModelHead.matchLeague = "match_league";
          
            oddsModelHead.sportteryHdcLines = "sportteryHdcLines";
            oddsModelHead.sportteryHdcHome = "sportteryHdcHome";
            oddsModelHead.sportteryHdcAway = "sportteryHdcAway";
            oddsModelHead.sportteryHdcUpdate = "sportteryHdcUpdate";
            oddsModelHead.sportteryHdcMargin = "sportteryHdcMargin";
            oddsModelHead.sportteryHiloLines = "sportteryHiloLines";
            oddsModelHead.sportteryHiloHigh = "sportteryHiloHigh";
            oddsModelHead.sportteryHiloLow = "sportteryHiloLow";
            oddsModelHead.sportteryHiloUpdate = "sportteryHiloUpdate";
            oddsModelHead.sportteryHiloMargin = "sportteryHiloMargin";
            
            
            oddsModelHead.sportteryAllModel = basketballSportteryEntity;
            oddsModelHead.sportteryAllModel.startDate = "startDate";
            oddsModelHead.sportteryAllModel.homeTeam = "homeTeam";
            oddsModelHead.sportteryAllModel.awayTeam = "awayTeam";
            oddsModelHead.sportteryAllModel.hdcHome = "hdcHome";
            oddsModelHead.sportteryAllModel.hdcAway = "hdcAway";
            oddsModelHead.sportteryAllModel.hdcLine = "hdcLine";
            oddsModelHead.sportteryAllModel.hiloHigh = "hiloHigh";
            oddsModelHead.sportteryAllModel.hiloLow = "hiloLow";
            oddsModelHead.sportteryAllModel.hiloLine = "hiloLine";
            oddsModelHead.sportteryAllModel.mnlHome = "mnlHome";
            oddsModelHead.sportteryAllModel.mnlAway = "mnlAway";
            oddsModelHead.sportteryAllModel.wnmHome15 = "wnmHome15";
            oddsModelHead.sportteryAllModel.wnmHome610 = "wnmHome610";
            oddsModelHead.sportteryAllModel.wnmHome1115 = "wnmHome1115";
            oddsModelHead.sportteryAllModel.wnmHome1620 = "wnmHome1620";
            oddsModelHead.sportteryAllModel.wnmHome2125 = "wnmHome2125";
            oddsModelHead.sportteryAllModel.wnmHome26Plus = "wnmHome26Plus";
            oddsModelHead.sportteryAllModel.wnmAway15 = "wnmAway15";
            oddsModelHead.sportteryAllModel.wnmAway610 = "wnmAway610";
            oddsModelHead.sportteryAllModel.wnmAway1115 = "wnmAway1115";
            oddsModelHead.sportteryAllModel.wnmAway1620 = "wnmAway1620";
            oddsModelHead.sportteryAllModel.wnmAway2125 = "wnmAway2125";
            oddsModelHead.sportteryAllModel.wnmAway26Plus = "wnmAway26Plus";
            oddsModelHead.sportteryAllModel.absenceState = "absenceState"; 
          
            //data.push(oddsModelHead);
            $.each($scope.oddsList, function (i, value) {
                if (value.choose) {
                    data.push(value);
                }
            }); 
        
            
            $.ajax({
                type: "POST",
                url: "matchBasketballOddsExcel.do",
                data: {
                    "oddsModels": JSON.stringify(data)
                },
                dataType: "json",
                success: function (data) {
                    window.open("download.do?downloadname=" + data.fileName);
                },
                error: function (XMLHttpRequest, txtStatus, errorThrown) {
                    alert(XMLHttpRequest + txtStatus + errorThrown);
                }
            });
        }
       
        $scope.validate = function (start, end) {
            if (start == "") {
                alert("start date can not be null");
                return false;
            }
            if (end == "") {
                alert("end date can not be null");
                return false;
            }
            if (end.substring(6, 10) - start.substring(6, 10) < 0) {
                alert("end date should be greater than start date");
                return false;
            }
            if (end.substring(6, 10) - start.substring(6, 10) == 0 && end.substring(0, 2) - start.substring(0, 2) < 0) {
                alert("end date should be greater than start date");
                return false;
            }
            if (end.substring(6, 10) - start.substring(6, 10) == 0 && end.substring(0, 2) - start.substring(0, 2) == 0 && end.substring(3, 5) - start.substring(3, 5) < 0) {
                alert("end date should be greater than start date");
                return false;
            }
            return true;
        }
    }); 
    
    
</script> 