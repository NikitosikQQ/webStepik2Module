package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login == null || pass == null || login.equals("") || pass.equals("")) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Некорректный запрос!");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);

        if (profile != null && !profile.getPassword().equals(pass)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Неверный логин или пароль!");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (profile != null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Пользователь с таким логином уже существует!");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        accountService.addNewUser(new UserProfile(login, pass));
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Успешная регистрация под логином: " + login);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
