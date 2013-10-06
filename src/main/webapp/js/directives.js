overseer.directive('booleanLabel', function factory() {
    return {
        template: '<span class="label label-{{class}}">{{label}}</span>',
        replace: true,
        restrict: 'E',
        scope: {
            text: '@text',
            status: '@status',
            success: '@success',
            failure: '@failure'
        },
        link: {
            pre: function preLink(scope, iElement, iAttrs, controller) {
                scope.class = scope.status == 'true' ? 'success' : 'danger';
                scope.label = scope.text ? scope.text : (scope.status == 'true' ? scope.success : scope.failure);
            }
        }
    };
});