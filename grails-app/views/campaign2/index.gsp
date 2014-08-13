
<%@ page import="twitter_manager_webserver.dto.CampaignDTO" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'campaign.label', default: 'Campaign')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-campaign" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-campaign" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'campaign.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="keywords" title="${message(code: 'campaign.keywords.label', default: 'Keywords')}" />
					
						<g:sortableColumn property="andRule" title="${message(code: 'campaign.andRule.label', default: 'And Rule')}" />
					
						<g:sortableColumn property="turnedOn" title="${message(code: 'campaign.turnedOn.label', default: 'Turned On')}" />

                        <th class="sortable">Report</th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${allCampaigns}" status="i" var="campaignInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${campaignInstance.id}">${fieldValue(bean: campaignInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: campaignInstance, field: "keywords")}</td>
					
						<td>${fieldValue(bean: campaignInstance, field: "andRule")}</td>
					
						<td><g:formatBoolean boolean="${campaignInstance.turnedOn}" /></td>

                        <td><g:link action="report" id="${campaignInstance.id}">view</g:link></td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${campaignInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
