import { authenticate, signup } from "./auth.js";

document.onreadystatechange = () => {
    $("#sin-b").click(authenticate);
    $("#sup-b").click(signup);
    $("#sign-up").hide();
    $("#alert-box-ok").hide();
    $("#lnk-sup").click(toggleSignUp);
    $("#lnk-sin").click(toggleSignUp);
};

const toggleSignUp = () => {
    $("#sign-in").toggle();
    $("#sign-up").toggle();
}