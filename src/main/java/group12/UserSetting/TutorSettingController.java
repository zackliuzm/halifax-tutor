package group12.UserSetting;

import group12.admin_setting.IAdminSettingDAO;
import group12.data_access.GetPlanSQLOperation;
import group12.data_access.Subscribe_Plan;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import group12.token_auth.IAccessToken;
import group12.token_auth.JWTAccessToken;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path="/tutor/setting")
public class TutorSettingController {
    private static final String SUCCESS = "SUCCESS";
    private static final String FAILURE = "FAILURE";
    private static final Logger logger = LogManager.getLogger(TutorSettingController.class);
    IAccessToken accessToken;
    ITutorSettingDAO tutorSettingDAO;

    public TutorSettingController(){
        accessToken = JWTAccessToken.getInstance();
        tutorSettingDAO = new TutorSettingDAO();
    }

    public TutorSettingController(IAccessToken accessToken, ITutorSettingDAO tutorSettingDAO){
        this.accessToken = accessToken;
        this.tutorSettingDAO = tutorSettingDAO;
    }


    @PostMapping(path="/password")
    public String changePassword(@RequestBody Map<String,String> body){
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO,email);
        logger.log(Level.INFO,body.get("password"));
        IEncryptor encryptor = new SimpleMD5Encryptor();
        String password = encryptor.encrypt(body.get("password"));
        logger.log(Level.INFO,password);
        if(tutorSettingDAO.setTutorPassword(email,password)){
            return SUCCESS;
        }
        else{
            return FAILURE;
        }
    }

    @PostMapping(path = "/email")
    public  String changeEmail(@RequestBody Map<String,String> body){
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO,email);
        logger.log(Level.INFO,body.get("email"));
        IEncryptor encryptor = new SimpleMD5Encryptor();
        String newemail = encryptor.encrypt(body.get("email"));
        logger.log(Level.INFO,newemail);
        if (tutorSettingDAO.setTutorEmail(email,newemail)){
            return SUCCESS;
        }else {
            return FAILURE;
        }
    }

    @PostMapping(path = "/card")
    public  String changeCardinfo(@RequestBody Map<String,String> body){
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO,email);
        logger.log(Level.INFO,body.get("cardname"));
        logger.log(Level.INFO,body.get("creditCardNumber"));
        logger.log(Level.INFO,body.get("expireDate"));
        logger.log(Level.INFO,body.get("securityCode"));

        IEncryptor encryptor = new SimpleMD5Encryptor();
        String cardname = encryptor.encrypt(body.get("cardname"));
        String creditCardNumber = encryptor.encrypt(body.get("creditCardNumber"));
        String expireDate = encryptor.encrypt(body.get("expireDate"));
        int securityCode = Integer.parseInt(encryptor.encrypt(body.get("securityCode")));

        logger.log(Level.INFO,cardname);
        logger.log(Level.INFO,creditCardNumber);
        logger.log(Level.INFO,expireDate);
        logger.log(Level.INFO,securityCode);

        if (tutorSettingDAO.setTutorCard(email,cardname,creditCardNumber,expireDate,securityCode)){
            return SUCCESS;
        }else {
            return FAILURE;
        }
    }


    @PostMapping(path="/education")
    public String changeEducation(@RequestBody Map<String,String> body){
        String email = accessToken.decodeToken(body.get("token"));
        logger.log(Level.INFO,email);
        logger.log(Level.INFO,body.get("education"));

        IEncryptor encryptor = new SimpleMD5Encryptor();
        String education = encryptor.encrypt(body.get("education"));
        logger.log(Level.INFO,education);
        if(tutorSettingDAO.setEducation(email,education)){
            return SUCCESS;
        }
        else{
            return FAILURE;
        }
    }

}
