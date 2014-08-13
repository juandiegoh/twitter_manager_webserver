<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
    <g:javascript src="jquery-1.9.1.js" />

    <g:javascript>
    <g:applyCodec encodeAs="none">

        $(function () {

            var hoursJSON = $.parseJSON('${hours}')
            var positiveJSON = $.parseJSON('${positiveValues}')
            var negativeJSON = $.parseJSON('${negativeValues}')

            $('#container').highcharts({
                title: {
                    text: 'Points by Hour',
                    x: -20 //center
                },
                subtitle: {
                    text: 'Campaign "${campaignName}" from ${dateFrom} to ${dateTo}',
                    x: -20
                },
                xAxis: {
                    categories: hoursJSON
                },
                yAxis: {
                    title: {
                        text: 'Points'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: ' Points'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: '${positiveLabel}',
                    data: positiveJSON
                }, {
                    name: '${negativeLabel}',
                    data: negativeJSON
                }]
            });
        });
    </g:applyCodec>
    </g:javascript>
</head>

<body>
<g:javascript src="highcharts.js" />
<g:javascript src="exporting.js" />

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

</body>
</html>