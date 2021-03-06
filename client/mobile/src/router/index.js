import Vue from 'vue';
import Router from 'vue-router';
import LandingPage from '@/components/LandingPage';
import SearchPage from '@/components/SearchPage';
import EntryDetailPage from '@/components/EntryDetailPage';
import UserProfilePage from '@/components/UserProfilePage';
import FAQPage from '@/components/FAQPage';
import WatchesPage from '@/components/WatchesPage';
import ResetPasswordPage from '@/components/ResetPasswordPage';
import SubmissionStatusPage from '@/components/SubmissionStatusPage';
import SubmissionCommentsPage from '@/components/SubmissionCommentsPage';
import SMEApprovalPage from '@/components/SMEApprovalPage';
import ContactPage from '@/components/ContactPage';
import WorkPlanProcess from '@/components/WorkPlanProcess';

Vue.use(Router);

export default new Router({
  // mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: LandingPage
    },
    {
      path: '/faq',
      name: 'F.A.Q.',
      component: FAQPage
    },
    {
      path: '/search',
      name: 'Search',
      component: SearchPage
    },
    {
      path: '/entry-detail/:id',
      name: 'Entry Detail',
      component: EntryDetailPage
    },
    {
      path: '/profile',
      name: 'User Profile',
      component: UserProfilePage
    },
    {
      path: '/watches',
      name: 'Watches',
      component: WatchesPage
    },
    {
      path: '/reset-password',
      name: 'Reset Password',
      component: ResetPasswordPage
    },
    {
      path: '/submission-status',
      name: 'Submission Status',
      component: SubmissionStatusPage
    },
    {
      path: '/submission-comments/:id',
      name: 'Submission Comments',
      component: SubmissionCommentsPage
    },
    {
      path: '/sme-approval',
      name: 'SME Approval',
      component: SMEApprovalPage
    },
    {
      path: '/contact/:ticket?',
      name: 'Contact',
      component: ContactPage
    },
    {
      path: '/work-plan-process/:id',
      name: 'Work Plan Process',
      component: WorkPlanProcess
    },
    {
      path: '*',
      redirect: '/' // Page not found
    }
  ]
});
