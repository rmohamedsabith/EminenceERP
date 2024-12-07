"use client"
import { Disclosure, DisclosureButton, DisclosurePanel, Menu, MenuButton, MenuItem, MenuItems } from '@headlessui/react'
import { Bars3Icon, XMarkIcon } from '@heroicons/react/24/outline'
import Link from 'next/link'
import { useState } from 'react'

const user = {
  name: 'Tom Cook',
  email: 'tom@example.com',
  imageUrl:
    'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80',
}

const userNavigation = [
  { name: 'Your Profile', href: '#' },
  { name: 'Settings', href: '#' },
  { name: 'Sign out', href: '#' },
]

function classNames(...classes: string[]) {
  return classes.filter(Boolean).join(' ')
}

export default function Header() {
  const [navigation,setNavigation] = useState([
    { name: 'Configuration',currentState:false, list:[
      { name: 'Category', href: '/configuration/category' },
      { name: 'Main Category', href: '/configuration/mainCategory' },
      { name: 'Branch', href: '/configuration/branch' },
      { name: 'Brand', href: '/configuration/brand' },
      { name: 'Supplier', href: '/configuration/supplier' },
    ]},
    { name: 'Stock Managment',currentState:false, list:[
      { name: 'Add Item', href: '#' },
      { name: 'View Stock', href: '#' },
      { name: 'Stock Transfer', href: '#' },
      { name: 'Stock Alert', href: '#' },
    ]},
    { name: 'Sales',currentState:false, list:[
      { name: 'Your Profile', href: '#' },
      { name: 'Settings', href: '#' },
      { name: 'Sign out', href: '#' },
    ]},
    { name: 'Reports',currentState:false, list:[
      { name: 'Your Profile', href: '#' },
      { name: 'Settings', href: '#' },
      { name: 'Sign out', href: '#' },
    ]},
    { name: 'Admin',currentState:false, list:[
      { name: 'Your Profile', href: '#' },
      { name: 'Settings', href: '#' },
      { name: 'Sign out', href: '#' },
    ]},
    { name: 'Transaction',currentState:false, list:[
      { name: 'Your Profile', href: '#' },
      { name: 'Settings', href: '#' },
      { name: 'Sign out', href: '#' },
    ]},
    { name: 'Customer',currentState:false, list:[
      { name: 'Your Profile', href: '#' },
      { name: 'Settings', href: '#' },
      { name: 'Sign out', href: '#' },
    ] },
    { name: 'Employee',currentState:false, list:[
      { name: 'Your Profile', href: '#' },
      { name: 'Settings', href: '#' },
      { name: 'Sign out', href: '#' },
    ] },
  ])
  const [currentHeader,setCurrentHeader] = useState("Dashboard");

  const handleNavClick = (clickedItemName:string) => {
    setNavigation((prev) =>
      prev.map((item) =>
        item.name === clickedItemName
          ? { ...item, currentState: !item.currentState }
          : { ...item, currentState: false }
      )
    );
  };
  
  return (
    <>
      <div className="min-h-full">
      {/* Navigation Bar */}
      <Disclosure as="nav" className="bg-gray-800">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
          <div className="flex h-16 items-center justify-between">
            <div className="flex items-center">
              <div className="shrink-0">
                <img
                  alt="Your Company"
                  src="https://tailwindui.com/plus/img/logos/mark.svg?color=indigo&shade=500"
                  className="h-8 w-8"
                />
              </div>
              <div className="hidden md:block">
                <div className="ml-10 flex items-baseline space-x-4">
                  {navigation.map((item) => (
                    <Menu as="div" className="relative" key={item.name}>
                      <div>
                        <MenuButton className="relative flex  items-center  bg-gray-800 text-sm">
                          <span className={classNames(
                          item.currentState ? 'bg-gray-900 text-white' : 'text-gray-300 hover:bg-gray-700 hover:text-white',
                          'rounded-md px-3 py-2 text-sm font-medium',
                        )} onClick={() => handleNavClick(item.name)}>{item.name}</span>
                        </MenuButton>
                      </div>
                      <MenuItems
                        transition
                        className="absolute left-0 z-10 mt-2 w-48 origin-top-left rounded-md bg-white py-1 shadow-lg ring-1 ring-black/5"
                      >
                        {item.list.map((subItem) => (
                          <MenuItem key={subItem.name}>
                            <Link
                              href={subItem.href}
                              className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                              onClick={()=>setCurrentHeader(subItem.name)}
                            >
                              {subItem.name}
                            </Link>
                          </MenuItem>
                        ))}
                      </MenuItems>
                    </Menu>
                  ))}
                </div>
              </div>
            </div>

            {/* User Profile */}
            <div className="hidden md:block">
              <div className="ml-4 flex items-center md:ml-6">
                <Menu as="div" className="relative ml-3">
                  <div>
                    <MenuButton className="relative flex max-w-xs items-center rounded-full bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800">
                      <span className="sr-only">Open user menu</span>
                      <img alt="" src={user.imageUrl} className="h-8 w-8 rounded-full" />
                    </MenuButton>
                  </div>
                  <MenuItems
                    transition
                    className="absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black/5"
                  >
                    {userNavigation.map((item) => (
                      <MenuItem key={item.name}>
                        <Link
                          href={item.href}
                          className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                        >
                          {item.name}
                        </Link>
                      </MenuItem>
                    ))}
                  </MenuItems>
                </Menu>
              </div>
            </div>

            {/* Mobile Menu Button */}
            <div className="-mr-2 flex md:hidden">
              <DisclosureButton className="group relative inline-flex items-center justify-center rounded-md bg-gray-800 p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800">
                <span className="sr-only">Open main menu</span>
                <Bars3Icon className="block h-6 w-6 group-data-[open]:hidden" />
                <XMarkIcon className="hidden h-6 w-6 group-data-[open]:block" />
              </DisclosureButton>
            </div>
          </div>
        </div>

        {/* Mobile Menu */}
        <DisclosurePanel className="md:hidden">
          <div className="space-y-1 px-2 pb-3 pt-2 sm:px-3">
            {navigation.map((item) => (
                      <Menu as="div" className="relative" key={item.name}>
                        <div>
                          <MenuButton className="relative flex  items-center  bg-gray-800 text-sm">
                            <span className={classNames(
                            item.currentState ? 'bg-gray-900 text-white' : 'text-gray-300 hover:bg-gray-700 hover:text-white',
                            'rounded-md px-3 py-2 text-sm font-medium',
                          )} onClick={() => handleNavClick(item.name)}>{item.name}</span>
                          </MenuButton>
                        </div>
                        <MenuItems
                          transition
                          className="absolute -left-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black/5"
                        >
                          {item.list.map((subItem) => (
                            <MenuItem key={subItem.name}>
                              <Link
                                href={subItem.href}
                                className="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100"
                              >
                                {subItem.name}
                              </Link>
                            </MenuItem>
                          ))}
                        </MenuItems>
                      </Menu>
                    ))}
          </div>
          <div className="border-t border-gray-700 pb-3 pt-4">
            <div className="flex items-center px-5">
              <div className="shrink-0">
                <img alt="" src={user.imageUrl} className="h-10 w-10 rounded-full" />
              </div>
              <div className="ml-3">
                <div className="text-base font-medium text-white">{user.name}</div>
                <div className="text-sm font-medium text-gray-400">{user.email}</div>
              </div>
            </div>
            <div className="mt-3 space-y-1 px-2">
              {userNavigation.map((item) => (
                 <Link href={item.href} key={item.name}>
                 <DisclosureButton
                   as="div"
                   className="block rounded-md px-3 py-2 text-base font-medium text-gray-400 hover:bg-gray-700 hover:text-white"
                 >
                   {item.name}
                 </DisclosureButton>
               </Link>
              ))}
            </div>
          </div>
        </DisclosurePanel>
      </Disclosure>
    </div>
      <header className="bg-white shadow">
      <div className="mx-auto max-w-7xl px-4 py-2 sm:px-6 lg:px-8">
        <h1 className="text-3xl font-bold tracking-tight text-gray-900">
          {currentHeader}
        </h1>
      </div>
    </header>
    </>
    
  )
}
