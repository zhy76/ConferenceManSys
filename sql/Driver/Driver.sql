/*
记录一下于mapper所有可用的sql语句
*/
-- 添加一个司机
INSERT INTO driver(driver_name, car_number, fleet_id, driver_pass, driver_phone, is_assign) 
VALUES('hcs', '121212', 1, '123456', '19914665732', 0)

INSERT INTO driver(driver_name, car_number, fleet_id, driver_pass, driver_phone, is_assign) 
VALUES(#{driverName}, #{carNumber}, #{fleetId}, #{driverPass}, #{driverPhone}, #{isAssign})

-- 按车队id查找司机
SELECT * FROM driver WHERE fleet_id=1;
SELECT * FROM driver WHERE fleet_id=#{fleetId};




-- 修改司机信息
UPDATE driver SET driver_name="gg", car_number="88888", fleet_id=1, driver_pass="6666", is_assign=true
WHERE driver_id=1;

UPDATE driver SET driver_name=#{driverName}, car_number=#{carNumber}, fleet_id=#{fleetId}, driver_pass=#{driverPass}, is_assign=#{isAssign} WHERE driver_id=#{driverId};

-- 修改司机状态
UPDATE driver SET is_assign=1 WHERE driver_id=1;
UPDATE driver SET is_assign=#{isAssign} WHERE driver_id=#{driverId};


-- 添加一个车队
INSERT INTO fleet(fleet_name, fleet_pass, fleet_phone) 
VALUES('glz车队', '12345', '1984537777');

INSERT INTO fleet(fleet_name, fleet_pass, fleet_phone) 
VALUES(#{fleetName}, #{fleetPass}, #{fleetPhone});

-- 按id查找车队
SELECT * FROM fleet WHERE fleet_id=1;
SELECT * FROM fleet WHERE fleet_id=#{fleetId};


-- 修改车队信息


-- pickUp

-- 增
INSERT INTO pick_up VALUES(1, 1, 1, "2020-10-10 10:00:00", "2020-10-10 20:00:00", true);
INSERT INTO pick_up VALUES(#{participantId}, #{driverId}, #{trainNumber}, #{toTime}, #{returnTime}, #{isFinishPickup});

-- 删
DELETE from pick_up where participant_id=1;
DELETE from pick_up where participant_id=#{participantId};
---------------------------------------------
DELETE from pick_up where driver_id=1;
DELETE from pick_up where driver_id=#{driverId};
---------------------------------------------
DELETE from pick_up where participant_id=1 AND driver_id=1;
DELETE from pick_up where participant_id=#{participantId} AND driver_id=#{driverId};
-- 查
SELECT * FROM pick_up;

-- 查询一个车队的接送信息
SELECT * FROM pick_up WHERE driver_id = 
(SELECT driver_id FROM fleet WHERE fleet_id=1);

SELECT * FROM pick_up WHERE driver_id = 
(SELECT driver_id FROM fleet WHERE fleet_id=#{fleetId});

-- 查询一个司机的接送信息
SELECT * FROM pick_up WHERE driver_id=1;
SELECT * FROM pick_up WHERE driver_id =#{driverId};

-- 
SELECT * FROM pick_up WHERE participant_id=1;
SELECT * FROM pick_up WHERE participant_id =#{participantId};

-- 改
UPDATE pick_up SET driver_id=1, train_number="1", to_time="2020-10-10 10:20:00", return_time="2020-10-10 10:20:00", is_finish_pickup=true WHERE participant_id=1;

UPDATE pick_up SET driver_id=#{driverId}, train_number=#{trainNumber}, to_time=#{toTime}, return_time=#{returnTime}, is_finish_pickup=#{isFinishPickup} WHERE participant_id=#{participantId};

-- 改
UPDATE pick_up SET participant_id=1, train_number="1", to_time="2020-10-10 10:10:00", return_time="2020-10-10 10:20:00", is_finish_pickup=true WHERE driver_id=1;

UPDATE pick_up SET participant_id=1, train_number="1", to_time="2020-10-10 10:10:00", return_time="2020-10-10 10:20:00", is_finish_pickup=true WHERE driver_id=1;
UPDATE pick_up SET participant_id=#{participantId}, train_number=#{trainNumber}, to_time=#{toTime}, return_time=#{returnTime}, is_finish_pickup=#{isFinishPickup} WHERE driver_id=#{driverId};





