<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if handle??>
        <#assign u = c.findBy(users, "handle", handle)!/>
    <#else>
        <#if user_id?? && user_id?is_number>
            <#assign u = c.findBy(users, "id", user_id)!/>
        </#if>
    </#if>

    <#if u?has_content>
        <div class="user">
            <div class="info">
                <div class="header"> <@c.userlink u false/> </div>
                <ul>
                    <li> id: ${u.id} </li>
                    <li> handle: ${u.handle} </li>
                    <li> name: ${u.name} </li>
                    <#assign num = posts?filter(x -> x.user_id == u.id)?size/>
                    <li> Количество постов: <a href="/posts?user_id=${u.id}" style="text-decoration: none"> ${num}</a></li>
                </ul>
            </div>
            <img src="/../img/face.jpg">
        </div>

        <div class="arrows">
            <#assign prevUser = c.findPrev(users, "handle", u.handle)!/>
            <@link prevUser "←"/>
            <#assign nextUser = c.findNext(users, "handle", u.handle)!/>
            <@link nextUser "→"/>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>

<#macro link u sign>
    <#if u?has_content>
        <a href="/user?handle=${u.handle}" style="text-decoration: none;"> ${sign} </a>
    <#else>
        ${sign}
    </#if>
</#macro>
