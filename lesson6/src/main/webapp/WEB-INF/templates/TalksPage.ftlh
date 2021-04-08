<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="talks" type="ru.itmo.wp.model.domain.Talk[]" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->

<#import "commons.ftlh" as c>

<@c.page>
    <#if error??>
        <div class="error">
            ${error!}
        </div>
    </#if>

    <div class="talks">
        <form method="post" action="">
            <input type="hidden" name="action" value="send"/>
            <div>
                <select name="targetUserId">
                    <option value=""> Choose receiver</option>
                        <#list users as user>
                            <option value="${user.id}">${user.login}</option>
                        </#list>
                </select>
            </div>
            <textarea name="talk" cols="50" rows="5"></textarea>
            <div>
                <input type="submit" value="Send message">
            </div>
        </form>
    </div>

    <div class="datatable">
        <div class="caption">Messages</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>From</th>
                <th>To</th>
                <th>Message</th>
                <th>When</th>
            </tr>
            </thead>
            <tbody>
            <#if talks??>
                <#list talks?reverse as talk>
                    <tr>
                        <td>${talk.id}</td>
                        <td>${c.findBy(users "id" talk.sourceUserId).login}</td>
                        <td>${c.findBy(users "id" talk.targetUserId).login}</td>
                        <td>${talk.text}</td>
                        <td>${talk.creationTime}</td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
