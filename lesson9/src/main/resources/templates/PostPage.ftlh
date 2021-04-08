<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#-- @ftlvariable name="comment" type="ru.itmo.wp.domain.Comment" -->
<#import "commons.ftlh" as c>

<@c.page>

    <#if post??>
        <@c.post post=post/>
        <#if user??>
            <div class="comment">
                <form method="post" action="">
                    <@c.error "comment.text"/>
                    <label>
                        <textarea name="text" rows="3" cols="50"></textarea>
                    </label>
                    <div class="button-field">
                        <input type="submit" value="Add comment">
                    </div>
                </form>
            </div>

        <#else>
            Only registered users can leave comments
        </#if>

        <#list post.comments as comment>
            <@c.comment comment/>
        </#list>

    <#else>
        No such post
    </#if>
</@c.page>
