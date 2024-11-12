package com.example.Tbank_fj_2024_COURSE_PROJECT.telegram.command.friendship;

import com.example.Tbank_fj_2024_COURSE_PROJECT.models.user.AppUser;
import com.example.Tbank_fj_2024_COURSE_PROJECT.services.FriendshipService;
import com.example.Tbank_fj_2024_COURSE_PROJECT.telegram.command.Command;
import com.example.Tbank_fj_2024_COURSE_PROJECT.telegram.command.friendship.FriendsMenuCommand;
import com.example.Tbank_fj_2024_COURSE_PROJECT.telegram.services.MessageSender;
import com.example.Tbank_fj_2024_COURSE_PROJECT.telegram.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RejectFriendRequestCommand implements Command {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private FriendshipService friendshipService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private FriendsMenuCommand friendsMenuCommand;

    @Override
    public void execute(String chatId, List<String> args) {
        AppUser currentUser = sessionService.getCurrentUser(chatId);
        if (currentUser != null) {
            friendshipService.rejectFriendRequest(currentUser.getUsername(), sessionService.getContext(chatId));
            messageSender.sendMessage(chatId, "Запрос от " + sessionService.getContext(chatId) + " отклонен.");
            sessionService.clearUserState(chatId);
            friendsMenuCommand.execute(chatId,null);
        } else {
            messageSender.sendMessage(chatId, "Вы не авторизованы. Используйте /login для входа.");
        }
    }
}
