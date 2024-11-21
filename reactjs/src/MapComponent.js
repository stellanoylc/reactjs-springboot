import React from 'react';
import { GoogleMap, Marker } from '@react-google-maps/api';
import { useSelector } from 'react-redux';

const containerStyle = {
  width: '100%',
  height: '400px',
};

const MapComponent = () => {
  const { mapCenter, selectedPlace } = useSelector((state) => state.location);

  return (
    <div style={{ marginTop: '20px' }}>
      <GoogleMap
        mapContainerStyle={containerStyle}
        center={mapCenter}
        zoom={12}
      >
        {selectedPlace && (
          <Marker position={mapCenter} />
        )}
      </GoogleMap>
    </div>
  );
};

export default MapComponent;