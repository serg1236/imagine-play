define(['angular','angularCookies','fileUpload'], function (ng) {
    'use strict';
    return ng.module('imagine.controllers', ['ngCookies','ngFileUpload']);
});