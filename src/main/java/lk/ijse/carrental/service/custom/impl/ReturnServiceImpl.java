package lk.ijse.carrental.service.custom.impl;

import lk.ijse.carrental.dao.DaoFactory;
import lk.ijse.carrental.dao.DaoType;
import lk.ijse.carrental.dao.custom.BookingDao;
import lk.ijse.carrental.dao.custom.ReturnDao;
import lk.ijse.carrental.dto.BookingDto;
import lk.ijse.carrental.dto.ReturnDto;
import lk.ijse.carrental.entity.BookingEntity;
import lk.ijse.carrental.entity.ReturnEntity;
import lk.ijse.carrental.service.custom.BookingService;
import lk.ijse.carrental.service.custom.ReturnService;

import java.util.ArrayList;
import java.util.List;

public class ReturnServiceImpl implements ReturnService {
    private BookingDao bookingDao = DaoFactory.getDao(DaoType.BOOKING);
    private ReturnDao returnDao = DaoFactory.getDao(DaoType.RETURN);

    @Override
    public void saveReturnDetails(ReturnDto returnDto) {
        BookingEntity bookingEntity = bookingDao.search(returnDto.getBookedId());
        var returnEntity = new ReturnEntity(
                returnDto.getId(),
                bookingEntity,
                returnDto.getReturnedDate(),
                returnDto.getOverdueDays(),
                returnDto.getOverdueAmount(),
                returnDto.getDamageCost(),
                returnDto.getFinalAmount());
        returnDao.save(returnEntity);
    }

    @Override
    public ReturnDto search(String returnId) {
        ReturnEntity returnEntity = returnDao.search(returnId);
        var returnDto = new ReturnDto(
                returnEntity.getId(),
                returnEntity.getBookedEntity().getId(),
                returnEntity.getReturnedDate(),
                returnEntity.getOverdueDays(),
                returnEntity.getOverdueAmount(),
                returnEntity.getDamageCost(),
                returnEntity.getFinalAmount());
        return returnDto;
    }

    @Override
    public List<ReturnDto> getAll() {
        List<ReturnDto> returnDtos = new ArrayList<>();
        List<ReturnEntity> returnEntities = returnDao.getAll();
        for (ReturnEntity entity : returnEntities) {
            var returnDto = new ReturnDto(
                    entity.getId(),
                    entity.getBookedEntity().getId(),
                    entity.getReturnedDate(),
                    entity.getOverdueDays(),
                    entity.getOverdueAmount(),
                    entity.getDamageCost(),
                    entity.getFinalAmount());
            returnDtos.add(returnDto);
        }
        return returnDtos;
    }

    @Override
    public void deleteReturnDetails(ReturnDto dto) {
        BookingEntity bookingEntity = bookingDao.search(dto.getBookedId());
        var returnEntity = new ReturnEntity(
                dto.getId(),
                bookingEntity,
                dto.getReturnedDate(),
                dto.getOverdueDays(),
                dto.getOverdueAmount(),
                dto.getDamageCost(),
                dto.getFinalAmount());
        returnDao.delete(returnEntity);
    }
}
