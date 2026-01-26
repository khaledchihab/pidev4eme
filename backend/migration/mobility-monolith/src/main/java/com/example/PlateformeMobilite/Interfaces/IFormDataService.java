package com.example.PlateformeMobilite.Interfaces;

import com.example.PlateformeMobilite.Entity.FormData;

import java.util.List;

public interface IFormDataService {
    public List<FormData> retrieveAllFormData();
    public FormData retrieveFormDataById(Long FormDataId);
    public FormData addFormData(FormData f);
    public FormData updateFormData(Long id,FormData f);
    public void removeFormData(Long FormDataId);
}
