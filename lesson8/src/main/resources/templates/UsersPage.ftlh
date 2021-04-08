<#-- @ftlvariable name="users" type="ru.itmo.wp.domain.User[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#-- @ftlvariable name="disabledForm" type="ru.itmo.wp.form.NoticeCredentials" -->

<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <@c.error "disabledForm.disabled"/>
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="3">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as usertd>
                    <tr>
                        <td class="id">${usertd.id}</td>
                        <td class="login">${usertd.login}</td>
                        <td class="creationTime">${usertd.creationTime}</td>
                        <td class="disabled">
                            <#if user.id != usertd.id>
                                <form method="post" action="">
                                    <input type="hidden" name="stringId" value=${usertd.id}>
                                    <button type="submit" name="disabled"
                                            value="<#if usertd.disabled>false<#else>true</#if>">
                                        <#if usertd.disabled>Enable<#else>Disable</#if>
                                    </button>
                                </form>
                            </#if>
                        </td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
