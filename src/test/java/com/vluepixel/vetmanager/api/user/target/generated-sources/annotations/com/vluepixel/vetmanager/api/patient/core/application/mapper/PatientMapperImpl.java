package com.vluepixel.vetmanager.api.patient.core.application.mapper;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.ProductSaleDto;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.ProductSale;
import com.vluepixel.vetmanager.api.client.core.application.dto.ClientDto;
import com.vluepixel.vetmanager.api.client.core.domain.model.Client;
import com.vluepixel.vetmanager.api.medicalrecord.core.application.dto.MedicalRecordDto;
import com.vluepixel.vetmanager.api.medicalrecord.core.domain.model.MedicalRecord;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.application.dto.TreatmentDto;
import com.vluepixel.vetmanager.api.medicalrecord.treatment.domain.model.Treatment;
import com.vluepixel.vetmanager.api.patient.core.application.dto.PatientDto;
import com.vluepixel.vetmanager.api.patient.core.domain.model.Patient;
import com.vluepixel.vetmanager.api.patient.core.domain.request.CreatePatientRequest;
import com.vluepixel.vetmanager.api.patient.core.domain.request.UpdatePatientRequest;
import com.vluepixel.vetmanager.api.patient.medicalhistory.application.dto.MedicalHistoryDto;
import com.vluepixel.vetmanager.api.patient.medicalhistory.domain.model.MedicalHistory;
import com.vluepixel.vetmanager.api.patient.race.application.dto.RaceDto;
import com.vluepixel.vetmanager.api.patient.race.domain.model.Race;
import com.vluepixel.vetmanager.api.patient.species.application.dto.SpeciesDto;
import com.vluepixel.vetmanager.api.patient.species.domain.model.Species;
import com.vluepixel.vetmanager.api.product.category.application.dto.CategoryDto;
import com.vluepixel.vetmanager.api.product.category.domain.model.Category;
import com.vluepixel.vetmanager.api.product.core.application.dto.ProductDto;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import com.vluepixel.vetmanager.api.user.core.application.dto.UserDto;
import com.vluepixel.vetmanager.api.user.core.domain.model.User;
import com.vluepixel.vetmanager.api.user.core.domain.model.enums.UserRole;
import com.vluepixel.vetmanager.api.vaccine.core.application.dto.VaccineDto;
import com.vluepixel.vetmanager.api.vaccine.core.domain.model.Vaccine;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-28T20:38:37-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public Patient.PatientBuilder toBuilder(Patient t) {
        if ( t == null ) {
            return null;
        }

        Patient.PatientBuilder patientBuilder = createPatientBuilder();

        patientBuilder.birthDate( t.getBirthDate() );
        patientBuilder.characteristics( StringUtilsMapper.trimString( t.getCharacteristics() ) );
        patientBuilder.deceased( t.isDeceased() );
        patientBuilder.deleted( t.isDeleted() );
        patientBuilder.gender( t.getGender() );
        patientBuilder.id( t.getId() );
        List<MedicalHistory> list = t.getMedicalHistories();
        if ( list != null ) {
            patientBuilder.medicalHistories( new ArrayList<MedicalHistory>( list ) );
        }
        List<MedicalRecord> list1 = t.getMedicalRecords();
        if ( list1 != null ) {
            patientBuilder.medicalRecords( new ArrayList<MedicalRecord>( list1 ) );
        }
        patientBuilder.name( StringUtilsMapper.trimString( t.getName() ) );
        patientBuilder.owner( t.getOwner() );
        patientBuilder.race( t.getRace() );
        List<Vaccine> list2 = t.getVaccines();
        if ( list2 != null ) {
            patientBuilder.vaccines( new ArrayList<Vaccine>( list2 ) );
        }

        return patientBuilder;
    }

    @Override
    public PatientDto toDto(Patient domain) {
        if ( domain == null ) {
            return null;
        }

        PatientDto.PatientDtoBuilder patientDto = PatientDto.builder();

        patientDto.birthDate( domain.getBirthDate() );
        patientDto.characteristics( StringUtilsMapper.trimString( domain.getCharacteristics() ) );
        patientDto.deceased( domain.isDeceased() );
        patientDto.gender( domain.getGender() );
        patientDto.id( domain.getId() );
        patientDto.medicalHistories( medicalHistoryListToMedicalHistoryDtoList( domain.getMedicalHistories() ) );
        patientDto.medicalRecords( medicalRecordListToMedicalRecordDtoList( domain.getMedicalRecords() ) );
        patientDto.name( StringUtilsMapper.trimString( domain.getName() ) );
        patientDto.owner( clientToClientDto( domain.getOwner() ) );
        patientDto.race( raceToRaceDto( domain.getRace() ) );
        patientDto.vaccines( vaccineListToVaccineDtoList( domain.getVaccines() ) );

        patientDto.age( domain.calculateAge() );

        return patientDto.build();
    }

    @Override
    public Patient.PatientBuilder fromRequest(CreatePatientRequest request) {
        if ( request == null ) {
            return null;
        }

        Patient.PatientBuilder patientBuilder = createPatientBuilder();

        patientBuilder.race( mapRaceIdToRace( request.getRaceId() ) );
        patientBuilder.owner( mapOwnerIdToOwner( request.getOwnerId() ) );
        patientBuilder.birthDate( request.getBirthDate() );
        patientBuilder.characteristics( StringUtilsMapper.trimString( request.getCharacteristics() ) );
        patientBuilder.deceased( request.isDeceased() );
        patientBuilder.gender( request.getGender() );
        patientBuilder.name( StringUtilsMapper.trimString( request.getName() ) );

        return patientBuilder;
    }

    @Override
    public Patient.PatientBuilder fromRequest(UpdatePatientRequest request) {
        if ( request == null ) {
            return null;
        }

        Patient.PatientBuilder patientBuilder = createPatientBuilder();

        patientBuilder.race( mapRaceIdToRace( request.getRaceId() ) );
        patientBuilder.owner( mapOwnerIdToOwner( request.getOwnerId() ) );
        patientBuilder.birthDate( request.getBirthDate() );
        patientBuilder.characteristics( StringUtilsMapper.trimString( request.getCharacteristics() ) );
        patientBuilder.deceased( request.isDeceased() );
        patientBuilder.gender( request.getGender() );
        patientBuilder.id( request.getId() );
        patientBuilder.name( StringUtilsMapper.trimString( request.getName() ) );

        return patientBuilder;
    }

    protected MedicalHistoryDto medicalHistoryToMedicalHistoryDto(MedicalHistory medicalHistory) {
        if ( medicalHistory == null ) {
            return null;
        }

        MedicalHistoryDto.MedicalHistoryDtoBuilder medicalHistoryDto = MedicalHistoryDto.builder();

        medicalHistoryDto.content( StringUtilsMapper.trimString( medicalHistory.getContent() ) );
        medicalHistoryDto.createdAt( medicalHistory.getCreatedAt() );
        medicalHistoryDto.id( medicalHistory.getId() );
        medicalHistoryDto.updatedAt( medicalHistory.getUpdatedAt() );

        return medicalHistoryDto.build();
    }

    protected List<MedicalHistoryDto> medicalHistoryListToMedicalHistoryDtoList(List<MedicalHistory> list) {
        if ( list == null ) {
            return null;
        }

        List<MedicalHistoryDto> list1 = new ArrayList<MedicalHistoryDto>( list.size() );
        for ( MedicalHistory medicalHistory : list ) {
            list1.add( medicalHistoryToMedicalHistoryDto( medicalHistory ) );
        }

        return list1;
    }

    protected TreatmentDto treatmentToTreatmentDto(Treatment treatment) {
        if ( treatment == null ) {
            return null;
        }

        TreatmentDto.TreatmentDtoBuilder treatmentDto = TreatmentDto.builder();

        treatmentDto.description( StringUtilsMapper.trimString( treatment.getDescription() ) );
        treatmentDto.id( treatment.getId() );
        treatmentDto.order( treatment.getOrder() );

        return treatmentDto.build();
    }

    protected List<TreatmentDto> treatmentListToTreatmentDtoList(List<Treatment> list) {
        if ( list == null ) {
            return null;
        }

        List<TreatmentDto> list1 = new ArrayList<TreatmentDto>( list.size() );
        for ( Treatment treatment : list ) {
            list1.add( treatmentToTreatmentDto( treatment ) );
        }

        return list1;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.email( StringUtilsMapper.trimString( user.getEmail() ) );
        userDto.firstName( StringUtilsMapper.trimString( user.getFirstName() ) );
        userDto.id( user.getId() );
        userDto.lastName( StringUtilsMapper.trimString( user.getLastName() ) );
        userDto.profileImageUrl( StringUtilsMapper.trimString( user.getProfileImageUrl() ) );
        List<UserRole> list = user.getRoles();
        if ( list != null ) {
            userDto.roles( new ArrayList<UserRole>( list ) );
        }

        return userDto.build();
    }

    protected MedicalRecordDto medicalRecordToMedicalRecordDto(MedicalRecord medicalRecord) {
        if ( medicalRecord == null ) {
            return null;
        }

        MedicalRecordDto.MedicalRecordDtoBuilder medicalRecordDto = MedicalRecordDto.builder();

        medicalRecordDto.diagnosis( StringUtilsMapper.trimString( medicalRecord.getDiagnosis() ) );
        medicalRecordDto.entryAt( medicalRecord.getEntryAt() );
        medicalRecordDto.heartRate( medicalRecord.getHeartRate() );
        medicalRecordDto.id( medicalRecord.getId() );
        medicalRecordDto.physicalExam( StringUtilsMapper.trimString( medicalRecord.getPhysicalExam() ) );
        medicalRecordDto.reason( StringUtilsMapper.trimString( medicalRecord.getReason() ) );
        medicalRecordDto.recipe( StringUtilsMapper.trimString( medicalRecord.getRecipe() ) );
        medicalRecordDto.respirationRate( medicalRecord.getRespirationRate() );
        medicalRecordDto.sterilized( medicalRecord.isSterilized() );
        medicalRecordDto.temperatureInCelsius( medicalRecord.getTemperatureInCelsius() );
        medicalRecordDto.treatments( treatmentListToTreatmentDtoList( medicalRecord.getTreatments() ) );
        medicalRecordDto.vet( userToUserDto( medicalRecord.getVet() ) );
        medicalRecordDto.weight( medicalRecord.getWeight() );

        return medicalRecordDto.build();
    }

    protected List<MedicalRecordDto> medicalRecordListToMedicalRecordDtoList(List<MedicalRecord> list) {
        if ( list == null ) {
            return null;
        }

        List<MedicalRecordDto> list1 = new ArrayList<MedicalRecordDto>( list.size() );
        for ( MedicalRecord medicalRecord : list ) {
            list1.add( medicalRecordToMedicalRecordDto( medicalRecord ) );
        }

        return list1;
    }

    protected ClientDto clientToClientDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDto.ClientDtoBuilder clientDto = ClientDto.builder();

        clientDto.address( StringUtilsMapper.trimString( client.getAddress() ) );
        List<String> list = client.getEmails();
        if ( list != null ) {
            clientDto.emails( new ArrayList<String>( list ) );
        }
        clientDto.firstName( StringUtilsMapper.trimString( client.getFirstName() ) );
        clientDto.id( client.getId() );
        clientDto.identification( StringUtilsMapper.trimString( client.getIdentification() ) );
        clientDto.identificationType( client.getIdentificationType() );
        clientDto.lastName( StringUtilsMapper.trimString( client.getLastName() ) );
        List<String> list1 = client.getPhones();
        if ( list1 != null ) {
            clientDto.phones( new ArrayList<String>( list1 ) );
        }

        return clientDto.build();
    }

    protected SpeciesDto speciesToSpeciesDto(Species species) {
        if ( species == null ) {
            return null;
        }

        SpeciesDto.SpeciesDtoBuilder speciesDto = SpeciesDto.builder();

        if ( species.getId() != null ) {
            speciesDto.id( species.getId().longValue() );
        }
        speciesDto.name( StringUtilsMapper.trimString( species.getName() ) );

        return speciesDto.build();
    }

    protected RaceDto raceToRaceDto(Race race) {
        if ( race == null ) {
            return null;
        }

        RaceDto.RaceDtoBuilder raceDto = RaceDto.builder();

        if ( race.getId() != null ) {
            raceDto.id( race.getId().longValue() );
        }
        raceDto.name( StringUtilsMapper.trimString( race.getName() ) );
        raceDto.species( speciesToSpeciesDto( race.getSpecies() ) );

        return raceDto.build();
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto.CategoryDtoBuilder categoryDto = CategoryDto.builder();

        if ( category.getId() != null ) {
            categoryDto.id( category.getId().longValue() );
        }
        categoryDto.name( StringUtilsMapper.trimString( category.getName() ) );

        return categoryDto.build();
    }

    protected List<CategoryDto> categoryListToCategoryDtoList(List<Category> list) {
        if ( list == null ) {
            return null;
        }

        List<CategoryDto> list1 = new ArrayList<CategoryDto>( list.size() );
        for ( Category category : list ) {
            list1.add( categoryToCategoryDto( category ) );
        }

        return list1;
    }

    protected ProductDto productToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.categories( categoryListToCategoryDtoList( product.getCategories() ) );
        productDto.description( StringUtilsMapper.trimString( product.getDescription() ) );
        productDto.id( product.getId() );
        productDto.name( StringUtilsMapper.trimString( product.getName() ) );
        productDto.price( product.getPrice() );
        productDto.quantity( product.getQuantity() );

        return productDto.build();
    }

    protected ProductSaleDto productSaleToProductSaleDto(ProductSale productSale) {
        if ( productSale == null ) {
            return null;
        }

        ProductSaleDto.ProductSaleDtoBuilder<?, ?> productSaleDto = ProductSaleDto.builder();

        productSaleDto.discount( productSale.getDiscount() );
        productSaleDto.id( productSale.getId() );
        productSaleDto.price( productSale.getPrice() );
        productSaleDto.seller( userToUserDto( productSale.getSeller() ) );
        productSaleDto.product( productToProductDto( productSale.getProduct() ) );
        productSaleDto.quantity( productSale.getQuantity() );

        return productSaleDto.build();
    }

    protected VaccineDto vaccineToVaccineDto(Vaccine vaccine) {
        if ( vaccine == null ) {
            return null;
        }

        VaccineDto.VaccineDtoBuilder vaccineDto = VaccineDto.builder();

        vaccineDto.doseInMilliliters( vaccine.getDoseInMilliliters() );
        vaccineDto.id( vaccine.getId() );
        vaccineDto.name( StringUtilsMapper.trimString( vaccine.getName() ) );
        vaccineDto.productSale( productSaleToProductSaleDto( vaccine.getProductSale() ) );
        vaccineDto.providedAt( vaccine.getProvidedAt() );
        vaccineDto.vaccinator( userToUserDto( vaccine.getVaccinator() ) );

        return vaccineDto.build();
    }

    protected List<VaccineDto> vaccineListToVaccineDtoList(List<Vaccine> list) {
        if ( list == null ) {
            return null;
        }

        List<VaccineDto> list1 = new ArrayList<VaccineDto>( list.size() );
        for ( Vaccine vaccine : list ) {
            list1.add( vaccineToVaccineDto( vaccine ) );
        }

        return list1;
    }
}
