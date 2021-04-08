<#import "./common.ftlh" as c>

<@c.page>
    <#list posts?sort_by("id")?reverse as post>
        <@c.makePost post true/>
    </#list>
</@c.page>
