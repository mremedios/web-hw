<#-- @ftlvariable name="post_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>

    <#if post_id?? && post_id?is_number>
        <#assign u = c.findBy(posts, "id", post_id)!/>
    </#if>

    <#if u?has_content>
            <@c.makePost u false/>
    <#else>
        No such post
    </#if>
</@c.page>

