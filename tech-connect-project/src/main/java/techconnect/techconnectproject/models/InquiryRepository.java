package techconnect.techconnectproject.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry,Integer> {
    List<Inquiry> findByType(String type);
    List<Inquiry> findByTypeAndResolved(String type, boolean resolved);
    List<Inquiry> findByUserIdAndTypeAndResolved(int userId, String type, boolean resolved);
    List<Inquiry> findByInqNumber(int inqNumber);
}
