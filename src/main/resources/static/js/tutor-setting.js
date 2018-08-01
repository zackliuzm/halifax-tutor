$(document).ready(function () {
    //Initialize tooltips
    $('.nav-tabs > li a[title]').tooltip();

    //Wizard
    $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {

        var $target = $(e.target);

        if ($target.parent().hasClass('disabled')) {
            return false;
        }
    });

    $(".next-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        $active.next().removeClass('disabled');
        nextTab($active);

    });
    $(".prev-step").click(function (e) {

        var $active = $('.wizard .nav-tabs li.active');
        prevTab($active);

    });

});
$(document).ready(function(){
    document.getElementById("upload_widget_opener").addEventListener("click", function() {
        cloudinary.openUploadWidget({ cloud_name: 'yrzzzzzz', upload_preset: 'ktpg8ic7', folder: 'user_photos'},
            function(error, result) { console.log(error, result) });
    }, false);
}) ;

$(document).on('cloudinarywidgetfileuploadsuccess', function(e, data) {
    console.log("Single file success", e, data);
});

function Addshow(id) {
    if (document.getElementById(id).style.display == 'block')
        document.getElementById(id).style.display = 'none';
    else
        document.getElementById(id).style.display = 'block'
}

function nextTab(elem) {
    $(elem).next().find('a[data-toggle="tab"]').click();
}
function prevTab(elem) {
    $(elem).prev().find('a[data-toggle="tab"]').click();
}



$(document).ready(function() {
    var token = localStorage.getItem("token");
    var objToken = {'token': token};

    $("#logout").click(function () {
        localStorage.removeItem("token");
    });

    $("#Cpassword-form").submit(function (event) {
        event.preventDefault();

        let password = $("#pwd").val();
        let data = {
            'token': token,
            'password': password
        };

        $.ajax({
            url: location.origin + "/tutor/setting/password",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (json) {
            let success = json['success'];
            if (success) {
                alert("Password change succeed!");
            } else {
                alert("Something went wrong with database...");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something went wrong on our back end...");
        });
    });

    $("#Cemail-form").submit(function (event) {
        event.preventDefault();
        var email = $("#email").val();

        var ChangeEmailData = {
            'token': token,
            'email': email
        };

        $.ajax({
            url: location.origin + "/tutor/setting/email",
            data: JSON.stringify(ChangeEmailData),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "SUCCESS") {
                alert("Email change succeed!");
            } else {
                alert(data);
            }
        }).fail(function (xhr, status, errorThrown) {

        });

    });

    $("#Cphone-form").submit(function (event) {
        event.preventDefault();
        let phone = $("#phone").val();

        var ChangePhoneData = {
            'token': token,
            'phone' : phone
        };

        $.ajax({
            url: location.origin +"/tutor/setting/phone",
            data: JSON.stringify(ChangePhoneData),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "Success") {
                alert("Phone Number change succeed!");
            } else {
                alert(data);
            }
        }).fail(function (xhr, status, errorThrown) {

        });

    });

    $("#Ccard-form").submit(function (event) {
        event.preventDefault();
        let creditCardNumber = $("#cardnum").val();
        let expireDate = $("#expire_date").val();
        let securityCode = $("#code").val();
        let cardname = $("#cardname").val();
        var ChangeCardData = {
            'token': token,
            'cardname': cardname,
            'creditCardNumber': creditCardNumber,
            'expireDate':expireDate,
            'securityCode' : securityCode
        };

        $.ajax({
            url: location.origin +"/tutor/setting/card",
            data: JSON.stringify(ChangeCardData),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data=== "Success") {
                alert("Card information change succeed!");
            } else {
                alert(data);
            }
        }).fail(function (xhr, status, errorThrown) {

        });

    });

    $("#Cedu").submit(function (event) {
        event.preventDefault();
        let education = $("#education").val();

        var ChangeEduData = {
            'token': token,
            'education' : education
        };

        $.ajax({
            url: location.origin +"/tutor/setting/education",
            data: JSON.stringify(ChangeEduData),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "Success") {
                alert("Education change succeed!");
            } else {
                alert(data);
            }
        }).fail(function (xhr, status, errorThrown) {

        });

    });

    $("#Cexperience").submit(function (event) {
        event.preventDefault();
        let experience = $("#experience").val();

        var ChangeExpData = {
            'token': token,
            'experience' : experience
        };

        $.ajax({
            url: location.origin +"/tutor/setting/experience",
            data: JSON.stringify(ChangeExpData),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "Success") {
                alert("Experience change succeed!");
            } else {
                alert(data);
            }
        }).fail(function (xhr, status, errorThrown) {

        });

    });

    $("#Cavailability").submit(function (event) {

        event.preventDefault();
        let su1 = $("#Su1").is(":checked");
        let mo1 = $("#Mo1").is(":checked");
        let tu1 = $("#Tu1").is(":checked");
        let we1 = $("#We1").is(":checked");
        let th1 = $("#Th1").is(":checked");
        let fr1 = $("#Fr1").is(":checked");
        let sa1 = $("#Sa1").is(":checked");

        let su2 = $("#Su2").is(":checked");
        let mo2 = $("#Mo2").is(":checked");
        let tu2 = $("#Tu2").is(":checked");
        let we2 = $("#We2").is(":checked");
        let th2 = $("#Th2").is(":checked");
        let fr2 = $("#Fr2").is(":checked");
        let sa2 = $("#Sa2").is(":checked");

        let su3 = $("#Su3").is(":checked");
        let mo3 = $("#Mo3").is(":checked");
        let tu3 = $("#Tu3").is(":checked");
        let we3 = $("#We3").is(":checked");
        let th3 = $("#Th3").is(":checked");
        let fr3 = $("#Fr3").is(":checked");
        let sa3 = $("#Sa3").is(":checked");

        let weeklySchedule = [
            [su1, su2, su3],
            [mo1, mo2, mo3],
            [tu1, tu2, tu3],
            [we1, we2, we3],
            [th1, th2, th3],
            [fr1, fr2, fr3],
            [sa1, sa2, sa3]
        ];

        let data = {
            'token': token,
            'weeklySchedule': weeklySchedule
        };

        $.ajax({
            url: location.origin +"/tutor/setting/weeklySchedule",
            data: JSON.stringify(data),
            contentType: "application/json",
            type: "POST",
            dataType: "json"
        }).done(function (data) {
            let success = data['success'];
            if (success) {
                alert("Availability change succeed!");
            } else {
                alert("Something went wrong in the backend...");
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Something went wrong...");
        });
    });

    $("#plan1").click(function (event) {
        event.preventDefault();

        var ChangePlan = {
            'token':token,
            'planNo':1
        };

        $.ajax({
            url: location.origin +"/tutor/setting/plan",
            data: JSON.stringify(ChangePlan),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "Success") {
                alert("Subscription Plan change succeed!");
            } else {
                alert(data);
            }
        }).fail(function (xhr, status, errorThrown) {

        });
    });

    $("#plan2").click(function (event) {
        event.preventDefault();

        var ChangePlan = {
            'token':token,
            'planNo':2
        };

        $.ajax({
            url: location.origin +"/tutor/setting/plan",
            data: JSON.stringify(ChangePlan),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data=== "Success") {
                alert("Subscription Plan change succeed!");
            } else {
                alert(data);
            }
        }).fail(function (xhr, status, errorThrown) {

        });
    });

    $("#plan3").click(function (event) {
        event.preventDefault();

        var ChangePlan = {
            'token':token,
            'planNo':3
        };

        $.ajax({
            url: location.origin +"/tutor/setting/plan",
            data: JSON.stringify(ChangePlan),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "Success") {
                alert("Subscription Plan change succeed!");
            } else {
                alert(data);
            }
        }).fail(function (xhr, status, errorThrown) {

        });
    });

    $("#plan4").click(function (event) {
        event.preventDefault();

        var ChangePlan = {
            'token':token,
            'planNo':4
        };

        $.ajax({
            url: location.origin +"/tutor/setting/plan",
            data: JSON.stringify(ChangePlan),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "Success") {
                alert("Subscription Plan change succeed!");
            } else {
                alert(data);
            }
        }).fail(function (xhr, status, errorThrown) {

        });
    });

    $("#resend").click(function (event) {
        event.preventDefault();



        var ResendEmail = {
            'token':token
        };

        $.ajax({
            url: location.origin +"/tutor/setting/resend",
            data: JSON.stringify(ChangePlan),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "Success") {
                alert("Confiem Email resend  succeed!");
            } else {
                alert(data);
            }
        }).fail(function (xhr, status, errorThrown) {

        });
    });

    $("#cancel").click(function (event) {
        event.preventDefault();

        var Cancel = {
            'token':token
        };

        $.ajax({
            url: location.origin +"/tutor/setting/cancel",
            data: JSON.stringify(Cancel),
            contentType: "application/json",
            type: "POST",
            dataType: "text"
        }).done(function (data) {
            if (data === "Success") {
                alert("Subscroption cancel succeed!");
            } else {
                alert(data);
            }
        }).fail(function (xhr, status, errorThrown) {

        });
    });

    // //get subscription info
    // $.ajax({
    //     url: location.origin +"/tutor/setting/postplan",
    //     // contentType: "application/json",
    //     type: "POST",
    //     dataType: "json"
    // }).done(function (data) {
    //     if (data.result === "Success") {
    //         //TODO POST TO HTML
    //     } else {
    //         alert(data.details);
    //     }
    // }).fail(function (xhr, status, errorThrown) {
    //
    // });
    //

});

