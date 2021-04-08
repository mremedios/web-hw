<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div>
        <#if user??>
            <p>id: ${user.id}</p>
            <p>login: ${user.login}</p>
            <p>creation time: ${user.creationTime}</p>
        <#else>
            <p>No such user</p>
        </#if>
    </div>
</@c.page>
