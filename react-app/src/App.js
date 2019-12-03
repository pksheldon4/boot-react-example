import React from 'react';
import { Admin, Resource } from 'react-admin';
import jsonServerProvider from 'ra-data-json-server';
import { GroupList } from './groups';

const dataProvider = jsonServerProvider('/api');
const App = () => (
     <Admin dataProvider={dataProvider}>
       <Resource name="groups" list={GroupList}/>
     </Admin>
);

export default App;
