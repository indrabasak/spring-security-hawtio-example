var LoginPlugin = (function(LoginPlugin) {

    LoginPlugin.pluginName = 'login-plugin';
    LoginPlugin.log = Logger.get('LoginPlugin');
    LoginPlugin.contextPath = "/hawtio/plugins/";
    LoginPlugin.templatePath = LoginPlugin.contextPath + "login-plugin/html/";

    LoginPlugin.module = angular.module('login-plugin', ['hawtioCore'])
        .config(function($routeProvider) {
            $routeProvider.
            when('/home', {
                templateUrl: '/hawtio/index.html'
            });
        });

    LoginPlugin.module.run(function(workspace, viewRegistry, layoutFull) {

        LoginPlugin.log.info(LoginPlugin.pluginName, " loaded");
        viewRegistry["login-plugin"] = layoutFull;
        workspace.topLevelTabs.push({
            id: "LoginPlugin",
            content: "Login Plugin",
            title: "Login plugin loaded dynamically",
            isValid: function(workspace) { return true; },
            href: function() { return "#/login-plugin"; },
            isActive: function(workspace) {
                return workspace.isLinkActive("login-plugin"); }

        });

    });

    LoginPlugin.LoginController = function($scope, $rootScope, $http) {
        var fullUrl = "/hawtio/index.html";
        $http({method: 'GET', url: fullUrl});
    };

    return LoginPlugin;

})(LoginPlugin || {});

hawtioPluginLoader.addModule(LoginPlugin.pluginName);