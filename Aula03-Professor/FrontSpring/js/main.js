import { check, signout } from "./auth.js";

document.onreadystatechange = () => {
    setInterval(check, 10000);
    $("#sout-b").click(signout)
}