package group12.ForgotPassword;

import group12.data_access.DatabaseInterface;
import group12.email.IMailer;
import group12.encryption.IEncryptor;
import group12.encryption.SimpleMD5Encryptor;
import group12.registration.RegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public class ForgotPasswordService implements IForgotPassword {

    private DatabaseInterface db;
    private IMailer mailer;
    private static Logger logger = LogManager.getLogger(RegistrationService.class);
    @Value("${email.sender}")
    String emailSender;

    @Value("${server.url}")
    String serverURL;

    public void setDb(DatabaseInterface db) {
        this.db = db;
    }

    public void setMailer(IMailer mailer) {
        this.mailer = mailer;
    }


    @Override
    public ForgotPasswordResponse verifyStudent(ForgotPasswordForm student) {

        ForgotPasswordResponse response = new ForgotPasswordResponse();

//        if (db.isEmailNew(email)) {
            int studentID = db.getStudentId(student.getEmail());
            UUID uuid = UUID.randomUUID();
            db.saveActivationCode(uuid.toString());
            serverURL = "http://localhost:8080";
            mailer.sendMail(emailSender, student.getEmail(), "Verification",
                    "Verification " + serverURL + "/student/studentid/" + studentID + "/email/" + student.getEmail() + "/verification/" + uuid.toString() + "/");
            response.setResult("Success");
//        }
//        else{
//            response.setResult("Failure");
////          response.addDetail("Tutor not Found");
//        }
            return response;
        }

    @Override
    public ForgotPasswordResponse verifyTutor(ForgotPasswordForm tutor) {

        ForgotPasswordResponse response = new ForgotPasswordResponse();

//        if (db.isEmailNew(tutor.getEmail())) {
            int tutorID = db.getTutorID(tutor.getEmail());
            UUID uuid = UUID.randomUUID();
            db.saveActivationCode(uuid.toString());
            serverURL = "http://localhost:8080";
            mailer.sendMail(emailSender, tutor.getEmail(), "Verification",
                    "Verification " + serverURL + "/tutor/tutorid/" + tutorID + "/email/" + tutor.getEmail() + "/verification/" + uuid.toString() + "/");
            response.setResult("Success");
//        }
//        else{
//            response.setResult("Failure");
////          response.addDetail("Tutor not Found");
//        }

        return response;
    }

    public String activateStudent(int studentID, String email, String activationCode) {
        try{
            db.activateStudent(studentID, activationCode);
        }catch(Exception e){
            logger.error(studentID + " " + activationCode, e);
            return "Activation Failed";
        }
        return "redirect:/html/set-new-password.html?usertype=student&email=" + email;
    }

    public String activateTutor(int tutorID, String email, String activationCode) {
        try{
            db.activateTutor(tutorID, activationCode);
        }catch(Exception e){
            logger.error(tutorID + " " + activationCode, e);
            return "Activation Failed";
        }
        return "redirect:/html/set-new-password.html?usertype=tutor&email=" + email;
    }


    @Override
    public ForgotPasswordResponse setNewPasswordStudent(ForgotPasswordForm student) {

        IEncryptor encryptor = new SimpleMD5Encryptor();

        ForgotPasswordResponse response = new ForgotPasswordResponse();

//        if (db.isEmailNew(email)) {
            if(db.updateStudentPassword(student.getEmail(), encryptor.encrypt(student.getPassword()))){
                response.setResult("Success");
            }
            else{
                response.setResult("Failure");
                response.addDetail("Internal Server Error");
            }
//        }
//        else{
//            response.setResult("Failure");
//            response.addDetail("Email already registered");
//        }


        return response;
    }

    @Override
    public ForgotPasswordResponse setNewPasswordTutor(ForgotPasswordForm tutor) {
        IEncryptor encryptor = new SimpleMD5Encryptor();

        ForgotPasswordResponse response = new ForgotPasswordResponse();

//        if (db.isEmailNew(email)) {
            if(db.updateTutorPassword(tutor.getEmail(), encryptor.encrypt(tutor.getPassword()))){
                response.setResult("Success");
            }
            else{
                response.setResult("Failure");
                response.addDetail("Internal Server Error");
            }
//        }
//        else{
//            response.setResult("Failure");
//            response.addDetail("Email already registered");
//        }


        return response;

    }

}
