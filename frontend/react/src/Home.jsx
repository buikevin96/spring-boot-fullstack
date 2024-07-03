import {Wrap, WrapItem, Spinner, Text} from '@chakra-ui/react'
import SidebarWithHeader from "./components/shared/Sidebar.jsx";
import { useEffect, useState } from "react";
import {getCustomers} from "./services/client.js";
import CardWithImage from "./components/Card.jsx";
import CreateCustomerDrawer from "./components/CreateCustomerDrawer.jsx";
import {errorNotification} from "./services/notification.js";

const Home = () => {

    return (
        <SidebarWithHeader>
            <Text fontSize={"6xl"}>Dashboard</Text>
        </SidebarWithHeader>
    )
}

export default Home;