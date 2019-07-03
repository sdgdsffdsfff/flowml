package com.beautiful.ui.core.service.impl;

import com.beautiful.ui.core.dao.BaseMongoDaoImpl;
import com.beautiful.ui.core.model.Connection;
import com.beautiful.ui.core.service.IConnectionService;
import org.springframework.stereotype.Service;

@Service
public class ConnectionService extends BaseMongoDaoImpl<Connection> implements IConnectionService {


}
