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

        $(function () {
            var countriesJSON = $.parseJSON('${countries}')
            var countriesPositiveJSON = $.parseJSON('${countriesPositive}')
            var countriesNegativeJSON = $.parseJSON('${countriesNegative}')
            var countriesNeutralJSON = $.parseJSON('${countriesNeutral}')

            $('#container2').highcharts({
                chart: {
                    type: 'bar'
                },

                colors: ['#0C8B1B','#5A7AFF','#FF3F3F'],

                title: {
                    text: 'Indice YourImpact por Pais'
                },
                xAxis: {
                    categories: countriesJSON
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'Cantidad de Tweets'
                    }
                },
                legend: {
                    reversed: true
                },
                plotOptions: {
                    series: {
                        stacking: 'normal'
                    }
                },
                series: [{
                    name: 'Positivos',
                    data: countriesPositiveJSON
                }, {
                    name: 'Neutrales',
                    data: countriesNeutralJSON
                }, {
                    name: 'Negativos',
                    data: countriesNegativeJSON
                }]
            });
        });

        $(function () {

            var followersCategories = $.parseJSON('${followersCategories}')
            var followersPositives = $.parseJSON('${followersPositives}')
            var followersNegatives = $.parseJSON('${followersNegatives}')

            $('#container3').highcharts({

                chart: {
                    type: 'column',
                    options3d: {
                        enabled: true,
                        alpha: 15,
                        beta: 15,
                        viewDistance: 25,
                        depth: 40
                    },
                    marginTop: 80,
                    marginRight: 40
                },

                colors: ['#0C8B1B','#FF3F3F'],

                title: {
                    text: 'Promedio Indice YourImpact sobre Categorias de Seguidores'
                },

                xAxis: {
                    categories: followersCategories
                },

                yAxis: {
                    allowDecimals: false,
                    min: 0,
                    title: {
                        text: 'Promedio Indice YourImpact'
                    }
                },

                tooltip: {
                    headerFormat: '<b>{point.key}</b><br>',
                    pointFormat: '<span style="color:{series.color}">\u25CF</span> {series.name}: {point.y}'
                },

                series: [{
                    name: 'Positivos',
                    data: followersPositives
                }, {
                    name: 'Negativos',
                    data: followersNegatives
                }]
            });
        });
    </g:applyCodec>
    </g:javascript>
</head>

<body>
<g:javascript src="highcharts.js" />
<g:javascript src="exporting.js" />
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><a class="list" href="${createLink(uri: '/campaign2')}"><g:message code="default.list.label" args="Campaign" /></a></li>
    </ul>
</div>

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<div id="container2" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<div id="container3" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

</body>
</html>