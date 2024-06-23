import {
    Button,
    Drawer,
    DrawerBody,
    DrawerCloseButton,
    DrawerContent,
    DrawerFooter,
    DrawerHeader,
    DrawerOverlay,
    useDisclosure
} from "@chakra-ui/react";
import CreateCustomerForm from "./shared/CreateCustomerForm.jsx";
<<<<<<< Updated upstream
=======
import UpdateCustomerForm from "./UpdateCustomerForm.jsx";
import React from "react";
>>>>>>> Stashed changes

const AddIcon = () => "+";
const CloseIcon = () => "x";

<<<<<<< Updated upstream
const UpdateCustomerDrawer = ({fetchCustomers, initialValues}) => {
    const { isOpen, onOpen, onClose } = useDisclosure()
    return <>
        <Button
            leftIcon={<AddIcon/>}
            colorScheme={"teal"}
            onClick={onOpen}
        >
            Create customer
=======
const UpdateCustomerDrawer = ({fetchCustomers, initialValues, customerId}) => {
    const { isOpen, onOpen, onClose } = useDisclosure()
    return <>
        <Button
            bg={"grey.200"}
            color={'black'}
            rounded={'full'}
            _hover={{
                transform: 'translateY(-2px)',
                boxShadow: 'lg'
            }}
            onClick={onOpen}
        >
            Update Customer
>>>>>>> Stashed changes
        </Button>
        <Drawer isOpen={isOpen} onClose={onClose} size={"xl"}>
            <DrawerOverlay />
            <DrawerContent>
                <DrawerCloseButton />
<<<<<<< Updated upstream
                <DrawerHeader>Create new customer</DrawerHeader>

                <DrawerBody>
                    <UpdateCustomerDrawer
                        fetchCustomers={fetchCustomers}
                        initialValues={initialValues}
=======
                <DrawerHeader>Update customer</DrawerHeader>

                <DrawerBody>
                    <UpdateCustomerForm
                        fetchCustomers={fetchCustomers}
                        initialValues={initialValues}
                        customerId={customerId}
>>>>>>> Stashed changes
                    />
                </DrawerBody>

                <DrawerFooter>
                    <Button
                        leftIcon={<CloseIcon/>}
                        colorScheme={"teal"}
                        onClick={onClose}>
                        Close
                    </Button>
                </DrawerFooter>
            </DrawerContent>
        </Drawer>
    </>

}
<<<<<<< Updated upstream
export default CreateCustomerDrawer;
=======
export default UpdateCustomerDrawer;
>>>>>>> Stashed changes
