package group12.UserSetting;

public class TutorSettingDAO implements ITutorSettingDAO{
    @Override
    public int countTutorByEmail(String email) {
        return 0;
    }

    @Override
    public boolean setTutorPassword(String email, String password) {
        return false;
    }

    @Override
    public boolean setTutorEmail(String email) {
        return false;
    }

    @Override
    public boolean setTutorPhone(String email, String phone) {
        return false;
    }

    @Override
    public boolean setTutorCard(String email, String creditCardHolder, String creditCardNum, String creditCardExpiryDate, int securityCode) {
        return false;
    }

    @Override
    public boolean setEdu(String email, String education) {
        return false;
    }

    @Override
    public boolean setExperience(String email, String experience) {
        return false;
    }

    @Override
    public boolean addCourse(String email, String school, int code, String price) {
        return false;
    }
}
