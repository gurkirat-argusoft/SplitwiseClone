import { Routes } from '@angular/router';
import { LayoutComponent } from './PrimeComponents/layout/layout.component';
import { HomeComponent } from './Components/home/home.component';
import { GroupComponent } from './Components/Groups/group/group.component';
import { GroupPageComponent } from './Components/Groups/group-page/group-page.component';
import { FriendListComponent } from './Components/Friends/friend-list/friend-list.component';
import { FriendPageComponent } from './Components/Friends/friend-page/friend-page.component';
import { AllTransactionsComponent } from './Components/History/all-transactions/all-transactions.component';
import { LoginComponent } from './PrimeComponents/login/login.component';
import { authGuard } from './Guards/auth.guard';
import { AddExpenseComponent } from './Components/Expenses/add-expense/add-expense.component';

export const routes: Routes = [
    {
        path:'',
        component:LayoutComponent,
        children:[
            {
                path:'home',
                component:HomeComponent
            },
            {
                path:'group',
                component:GroupComponent,
                
            },
            {
                path:'group/groupPage/:id',
                component:GroupPageComponent
            },
            {
                path:'friends',
                component:FriendListComponent
            },
            {
                path:'friends/friendPage',
                component:FriendPageComponent
            },
            {
                path:'history',
                component:AllTransactionsComponent
            }
        ],
        // canActivate:[authGuard]
    },
    {
        path:'login',
        component:LoginComponent,
        
    },
    {
        path:'',
        redirectTo:'login',
        pathMatch:'full'
    }
    ,
    {
        path:'expense',
        //redirectTo:'login',
        component:AddExpenseComponent
    }
];
