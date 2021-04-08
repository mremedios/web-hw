<#-- @ftlvariable name="noticeForm" type="ru.itmo.wp.form.NoticeCredentials" -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="notice-form">
        <form method="post" action="">
            <label>
                <textarea name="content" rows="15" cols="50">${noticeForm.content!}</textarea>
            </label>
            <@c.error "noticeForm.content"/>
            <div class="button-field">
                <input type="submit" value="Add">
            </div>
        </form>
    </div>
</@c.page>
