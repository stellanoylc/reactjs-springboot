import { configureStore } from '@reduxjs/toolkit';
import locationReducer from './locationSlice';
import dataReducer from './dataSlice';

export const store = configureStore({
  reducer: {
    location: locationReducer,
    data: dataReducer,
  },
});