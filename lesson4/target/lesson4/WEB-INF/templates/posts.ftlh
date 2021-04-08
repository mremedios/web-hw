<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#assign u = c.findBy(users, "id", user_id)!/>

    <#if u?has_content>
            <#list posts?filter(x -> x.user_id == u.id) as post>
                <@c.makePost post false/>
            </#list>
    <#else>
        No such user
    </#if>
</@c.page>

