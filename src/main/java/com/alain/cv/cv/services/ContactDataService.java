package com.alain.cv.cv.services;

import com.alain.cv.cv.entities.ContactData;
import java.util.List;

public interface ContactDataService {
    ContactData saveContactData(ContactData contactData);
    ContactData updateContactData(ContactData cData);
    List<ContactData> findAllContactDataByUserId(Long userId);
    void deleteContactData(Long id);

}
